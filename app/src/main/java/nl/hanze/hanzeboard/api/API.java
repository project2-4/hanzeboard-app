package nl.hanze.hanzeboard.api;

import android.content.Context;

import nl.hanze.hanzeboard.api.interceptors.AuthTokenInject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static final String BASE_URL = "https://moetikwcpapierhalen.nl/api/";
    public static final String STORAGE_URL = "https://moetikwcpapierhalen.nl/storage/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Context context, Class<S> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(new AuthTokenInject(context));
        httpClient.addInterceptor(logging);

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
