package pl.damianlegutko.demo.api.test.crud.framework.retrofit;

import com.google.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitFactory {
    HttpLoggingInterceptorFactory httpLoggingInterceptorFactory;

    @Inject
    public RetrofitFactory(HttpLoggingInterceptorFactory httpLoggingInterceptorFactory) {
        this.httpLoggingInterceptorFactory = httpLoggingInterceptorFactory;
    }

    public Retrofit createRetrofitInstance(String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptorFactory.createHttpLoggingInterceptor())
                .followRedirects(false);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
