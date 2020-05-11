package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import nl.hanze.hanzeboard.R;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthTokenGrabber implements Interceptor {

    private static final String TAG = "AuthTokenGrabber";

    private Context context;
    private SharedPreferences tokensPreferences;

    public AuthTokenGrabber(Context context) {
        this.context = context;
        tokensPreferences = context
                .getApplicationContext()
                .getSharedPreferences(context.getString(R.string.key_tokens), Context.MODE_PRIVATE);
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        Headers headers = response.headers();

        if (response.isSuccessful() && headers.names().contains("Authorization")) {
            tokensPreferences
                    .edit()
                    .putString(context.getString(R.string.key_jwt_token), headers.get("Authorization"))
                    .apply();

            Log.d(TAG, "Access token = " + headers.get("Authorization"));
        }

        return response;
    }
}
