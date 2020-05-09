package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.api.responses.LoginResponse;
import okhttp3.Interceptor;
import okhttp3.Request;
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

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(chain.request());

        if(request.url().toString().contains("auth/login")) { // Hmmm?
            if(response.isSuccessful() && response.body() != null) {
                LoginResponse loginResponse = new Gson().fromJson(response.body().string(), LoginResponse.class);

                tokensPreferences
                        .edit()
                        .putString(context.getString(R.string.key_jwt_token), loginResponse.getAccessToken())
                        .apply();

                Log.d(TAG, "Access token = " + loginResponse.getAccessToken());
            }
        }


        return response;
    }
}
