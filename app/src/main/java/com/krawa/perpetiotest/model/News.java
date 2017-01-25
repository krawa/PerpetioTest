package com.krawa.perpetiotest.model;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("_id")
    private String id;
    private String title;
    private String subtitle;
    private Thumb thumb;
    private String author;
    private String categoryId;
    private String content;
    private long date;
    private long update;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Thumb getThumb() {
        return thumb;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getContent() {
        return content;
    }

    public long getDate() {
        return date;
    }

    public long getUpdate() {
        return update;
    }
}
