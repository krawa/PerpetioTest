package com.krawa.perpetiotest.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.krawa.perpetiotest.di.scope.PerApplication;
import com.krawa.perpetiotest.network.RestAPI;
import com.krawa.perpetiotest.network.RestClient;
import com.krawa.perpetiotest.network.adapter.NewsFeedResponseAdapter;
import com.krawa.perpetiotest.network.response.NewsFeedResponse;
import com.krawa.perpetiotest.utils.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetModule {

    private static final String TAG = "NetModule";

    @Provides
    @PerApplication
    RestAPI provideRestAPI(RestClient restClient){
        return restClient.createRestAPI();
    }

    @Provides
    @PerApplication
    RestClient provideRestClient(OkHttpClient okHttpClient, Gson gson){
        return new RestClient(okHttpClient, gson);
    }

    @Provides
    @PerApplication
    OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if(Constants.IS_DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }
        return clientBuilder.build();
    }

    @Provides
    @PerApplication
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(NewsFeedResponse.class, new NewsFeedResponseAdapter())
                .create();
    }

}
