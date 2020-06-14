package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;

import nl.hanze.hanzeboard.R;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    private SharedPreferences cookiePreferences;

    public AddCookiesInterceptor(Context context) {
        cookiePreferences = context
                .getApplicationContext()
                .getSharedPreferences(context.getString(R.string.http_cookies), Context.MODE_PRIVATE);
    }

    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> cookies = (HashSet<String>) cookiePreferences.getStringSet("HTTP_COOKIES", new HashSet<>());

        Request original = chain.request();
        if(original.url().toString().contains("refresh")) {
            for (String cookie : cookies) {
                builder.addHeader("Cookie", cookie);
            }
        }

        return chain.proceed(builder.build());
    }
}
