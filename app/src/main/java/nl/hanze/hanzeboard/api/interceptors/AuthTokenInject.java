package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.LoginActivity;
import nl.hanze.hanzeboard.activities.overview.OverviewActivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthTokenInject implements Interceptor {

    private static final String TAG = "AuthTokenInject";

    private Context context;
    private SharedPreferences tokensPreferences;

    public AuthTokenInject(Context context) {
        this.context = context;
        tokensPreferences = context
                .getApplicationContext()
                .getSharedPreferences(context.getString(R.string.key_tokens), Context.MODE_PRIVATE);
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request.newBuilder()
                .addHeader("Accept", "application/json")
                .build();

        if (tokensPreferences.contains(context.getString(R.string.key_jwt_token)) && !request.headers().names().contains("Authorization")) {
            String authToken = "Bearer " + tokensPreferences.getString(context.getString(R.string.key_jwt_token), "INVALID");

            request = request.newBuilder()
                    .addHeader("Authorization", authToken)
                    .build();

            Log.d(TAG, "Access token = " + authToken);
        } else {
            Log.d(TAG, "Access token not set");
        }

        Response response = chain.proceed(request);

        if (response.code() == 401) {
            synchronized (this) {
                int code = refreshToken() / 100;
                if(code != 2) {
                    logout();
                }

                // Repeat request with new token
                String newAccessToken = tokensPreferences.getString(context.getString(R.string.key_jwt_token), "INVALID");

                if(newAccessToken != null) {
                    return chain.proceed(request.newBuilder()
                            .addHeader("Authorization", newAccessToken)
                            .build());
                }
            }
        }

        return response;
    }

    private int refreshToken() {
        //Refresh token, synchronously, save it, and return result code
        //you might use retrofit here
        return 500;
    }

    private void logout() {
        //logout your user
    }
}
