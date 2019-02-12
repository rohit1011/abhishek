package com.example.myapplication.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit=null;
    public static String BASE_URL="http://demo4527408.mockable.io/";

    public  static Retrofit getApiClient(){
        if(retrofit==null){

            Retrofit.Builder retrofitBuilder=new Retrofit.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder okhttp=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                    .cache(null);
            okhttp.callTimeout(20,TimeUnit.SECONDS);
            retrofit=retrofitBuilder.baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttp.build())
                    .build();
        }
        return retrofit;
    }
}
