package com.geekbrains.util;

import lombok.experimental.UtilityClass;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
@UtilityClass
public class RetrofitUtils {
    private static final String API_URL = "https://minimarket1.herokuapp.com/";
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new PrettyLogger());
    // OkHttpClient httpClient;


    public Retrofit getRetrofit() throws MalformedURLException {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMinutes(2l))
                .addInterceptor(logging.setLevel(BODY))
                .build();
        return new Retrofit.Builder()
                .baseUrl(new URL(API_URL))
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient)
                .build();



    }

}
