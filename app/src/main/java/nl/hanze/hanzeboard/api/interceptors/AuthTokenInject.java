package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.LoginActivity;
import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.UserClient;
import nl.hanze.hanzeboard.api.responses.LoginResponse;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class AuthTokenInject implements Interceptor {

    private static final String TAG = "AuthTokenInject";
    private static final ConditionVariable LOCK = new ConditionVariable(true);
    private static final AtomicBoolean mIsRefreshing = new AtomicBoolean(false);

    private Context context;
    private SharedPreferences tokensPreferences;
    private SharedPreferences cookiePreferences;

    public AuthTokenInject(Context context) {
        this.context = context;
        tokensPreferences = context
                .getApplicationContext()
                .getSharedPreferences(context.getString(R.string.key_tokens), Context.MODE_PRIVATE);

        cookiePreferences = context
                .getApplicationContext()
                .getSharedPreferences(context.getString(R.string.http_cookies), Context.MODE_PRIVATE);
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder()
                .removeHeader("Accept")
                .addHeader("Accept", "application/json");

        if (tokensPreferences.contains(context.getString(R.string.key_jwt_token)) && !request.headers().names().contains("Authorization")) {
            builder = addToken(builder);
        } else {
            Log.d(TAG, "Access token not set");
        }

        Response response = chain.proceed(builder.build());

        if (response.code() == 401) {
            if (tokensPreferences.contains(context.getString(R.string.key_jwt_token))) {
                // Because we send out multiple HTTP requests in parallel, they might all list a 401 at the same time.
                // Only one of them should refresh the token.
                if (mIsRefreshing.compareAndSet(false, true)) {
                    LOCK.close();

                    int code = refreshToken();

                    LOCK.open();
                    mIsRefreshing.set(false);

                    if (code != 200) {
                        logout();
                    }
                } else {
                    // Another thread is refreshing the token for us, let's wait for it.
                    LOCK.block();

                    // another thread has refreshed this for us! thanks!
                    // sign the request with the new token and proceed
                    // return the outcome of the newly signed request
                    response = chain.proceed(addToken(request.newBuilder()).build());
                }
            }
        }

        return response;
    }

    private Request.Builder addToken(Request.Builder builder) {
        String authToken = "Bearer " + tokensPreferences.getString(context.getString(R.string.key_jwt_token), "INVALID");

        Log.d(TAG, "Access token = " + authToken);

        return builder.addHeader("Authorization", authToken);
    }

    private int refreshToken() throws IOException {
        UserClient userClient = API.createService(context, UserClient.class);
        Call<LoginResponse> call = userClient.refresh();
        retrofit2.Response<LoginResponse> response = call.execute();

        if (response.body() == null) {
            return response.code();
        }

        tokensPreferences
                .edit()
                .putString(context.getString(R.string.key_jwt_token), response.body().getAccessToken())
                .apply();

        return response.code();
    }

    private void logout() {
        tokensPreferences.edit().clear().apply();
        cookiePreferences.edit().clear().apply();

        AppCompatActivity context = (AppCompatActivity) this.context;

        context.startActivity(new Intent(context, LoginActivity.class));
        context.finish();
    }
}
