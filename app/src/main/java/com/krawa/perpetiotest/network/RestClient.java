package com.krawa.perpetiotest.network;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String TAG = "RestClient";

    private final static String BASE_URL = "http://figaro.service.yagasp.com/";

    protected final Retrofit retrofit;

    public RestClient(OkHttpClient okHttpClient, Gson gson){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public RestAPI createRestAPI(){
        return retrofit.create(RestAPI.class);
    }

}
