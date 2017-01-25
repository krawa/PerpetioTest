package com.krawa.perpetiotest.network.response;

import com.krawa.perpetiotest.model.News;

import java.util.ArrayList;

public class NewsFeedResponse {

    private ArrayList<News> newsList;
    private long timestamp;

    public NewsFeedResponse(ArrayList<News> newsList, long timestamp) {
        this.newsList = newsList;
        this.timestamp = timestamp;
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
