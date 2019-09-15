package com.ucas.lectureregistrationsystem.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager mInstance;
    private static String baseUrl="http://172.20.17.57:8081";
    private static String publicBaseUrl="http://112.74.63.8:8081";
    private static String LocalBaseUrl="http://124.16.125.183:8080";
    private static String LocalBaseTEXTUrl="http://124.16.125.183:8081";

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }


    public Retrofit getRetrofit(){
        Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(LocalBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

}
