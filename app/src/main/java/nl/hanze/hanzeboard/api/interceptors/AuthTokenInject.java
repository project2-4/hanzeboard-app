package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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
                .addHeader("Accept", "application/json");

        if (tokensPreferences.contains(context.getString(R.string.key_jwt_token)) && !request.headers().names().contains("Authorization")) {
            String authToken = "Bearer " + tokensPreferences.getString(context.getString(R.string.key_jwt_token), "INVALID");

            builder.addHeader("Authorization", authToken);

            Log.d(TAG, "Access token = " + authToken);
        } else {
            Log.d(TAG, "Access token not set");
        }

        Response response = chain.proceed(builder.build());

        if (response.code() == 401) {
            synchronized (this) {
                int code = refreshToken() / 100;
                if(code != 2) {
                    logout();
                }

                String newAccessToken = tokensPreferences.getString(context.getString(R.string.key_jwt_token), null);

                if(newAccessToken != null) {
                    return chain.proceed(request.newBuilder()
                            .addHeader("Authorization", newAccessToken)
                            .build());
                }
            }
        }

        return response;
    }

    private int refreshToken() throws IOException {
        UserClient userClient = API.createService(context, UserClient.class);
        Call<LoginResponse> call = userClient.refresh();
        retrofit2.Response<LoginResponse> response = call.execute();

        if (response.body() == null) {
            return 500;
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
