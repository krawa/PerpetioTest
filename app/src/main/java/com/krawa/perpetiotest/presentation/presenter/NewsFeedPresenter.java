package com.krawa.perpetiotest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.krawa.perpetiotest.App;
import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.network.RestAPI;
import com.krawa.perpetiotest.network.response.NewsFeedResponse;
import com.krawa.perpetiotest.presentation.view.NewsFeedView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class NewsFeedPresenter extends BaseListFragmentPresenter<NewsFeedView> {

    private static final String NEWS_FEED_CATEGORY_ID = "QWN0dWFsaXTDqXNBY3R1YWxpdMOpcw==";

    @Inject
    RestAPI restAPI;

    public NewsFeedPresenter(){
        App.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setTitle(R.string.news_feed);
    }

    @Override
    protected void getItems(int page) {
        restAPI.getNewsFeed(NEWS_FEED_CATEGORY_ID).enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(Call<NewsFeedResponse> call, Response<NewsFeedResponse> response) {
                getViewState().showListProgress(false);
                String error = null;
                if(response.isSuccessful()){
                    getViewState().updateList(response.body().getNewsList(), false, true);
                    getViewState().setListPosition(0);
                } else {
                    error = response.message();
                }
                getViewState().setEmptyText(error);
            }

            @Override
            public void onFailure(Call<NewsFeedResponse> call, Throwable t) {
                getViewState().showListProgress(false);
                getViewState().setEmptyText(t.getMessage());
            }
        });
    }


}
