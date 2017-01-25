package com.krawa.perpetiotest.network;

import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.network.response.NewsFeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI {

    @GET("article/header/{categoryId}")
    Call<NewsFeedResponse> getNewsFeed(@Path("categoryId") String categoryId);

    @GET("article/{articleId}")
    Call<News> getArticle(@Path("articleId") String articleId);

}
