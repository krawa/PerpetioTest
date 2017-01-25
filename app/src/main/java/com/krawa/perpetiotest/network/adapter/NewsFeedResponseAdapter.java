package com.krawa.perpetiotest.network.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.network.response.NewsFeedResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NewsFeedResponseAdapter implements JsonDeserializer<NewsFeedResponse> {

    @Override
    public NewsFeedResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArr = json.getAsJsonArray();
        long timestamp = jsonArr.get(0).getAsJsonArray().get(0).getAsJsonObject().get("timestamp").getAsLong();

        ArrayList<News> newsList = new ArrayList<>();
        JsonArray newsArr = jsonArr.get(1).getAsJsonArray();
        for (JsonElement jsonElement : newsArr) {
            News news = context.deserialize(jsonElement, News.class);
            newsList.add(news);
        }

        return new NewsFeedResponse(newsList, timestamp);
    }

}
