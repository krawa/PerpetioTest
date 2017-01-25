package com.krawa.perpetiotest.model;

import java.io.Serializable;

public class Thumb implements Serializable{

    private String link;
    private String md5;

    public String getLink() {
        return link;
    }

    public String getMd5() {
        return md5;
    }
}
