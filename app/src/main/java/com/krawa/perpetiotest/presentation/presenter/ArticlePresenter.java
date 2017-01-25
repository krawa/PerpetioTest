package com.krawa.perpetiotest.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.krawa.perpetiotest.App;
import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.network.RestAPI;
import com.krawa.perpetiotest.presentation.view.ArticleView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class ArticlePresenter extends MvpPresenter<ArticleView> {

    private static final String TAG = "ArticlePresenter";

    private News news;

    @Inject
    RestAPI restAPI;

    public ArticlePresenter(){
        App.getAppComponent().inject(this);
    }

    public void init(News news) {
        this.news = news;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setTitle(R.string.news_feed);
        getViewState().bindArticle(news);
        loadDetails(news.getId());
    }

    private void loadDetails(String articleId) {
        Log.d(TAG, "loadDetails() called with: articleId = [" + articleId + "]");
        restAPI.getArticle(articleId).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful()){
                    getViewState().bindArticle(response.body());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
