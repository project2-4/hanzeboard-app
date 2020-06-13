package nl.hanze.hanzeboard.api.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;

import nl.hanze.hanzeboard.R;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    private SharedPreferences cookiePreferences;

    public ReceivedCookiesInterceptor(Context context) {
        cookiePreferences = context
                .getApplicationContext()
                .getSharedPreferences(context.getString(R.string.http_cookies), Context.MODE_PRIVATE);
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = (HashSet<String>) cookiePreferences.getStringSet("HTTP_COOKIES", new HashSet<>());

            cookies.addAll(originalResponse.headers("Set-Cookie"));

            cookiePreferences.edit()
                    .putStringSet("HTTP_COOKIES", cookies)
                    .apply();
        }

        return originalResponse;
    }
}