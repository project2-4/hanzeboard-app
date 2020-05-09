package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.api.responses.LoginResponse;
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

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if(tokensPreferences.contains(context.getString(R.string.key_jwt_token))) {
            String authToken = tokensPreferences.getString(context.getString(R.string.key_jwt_token), "INVALID");

            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + authToken)
                    .build();

            Log.d(TAG, "Access token = " + authToken);
        } else {
            Log.d(TAG, "Access token not set");
        }

        return chain.proceed(request);
    }
}
