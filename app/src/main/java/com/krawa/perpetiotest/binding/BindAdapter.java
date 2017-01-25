package com.krawa.perpetiotest.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

public class BindAdapter {

    private static final String TAG = "BindAdapter";

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, final String url) {
        if(url == null || url.isEmpty()) return;
        Picasso picasso = Picasso.with(view.getContext().getApplicationContext());
        RequestCreator rc;
        if(url.startsWith("/")){
            rc = picasso.load(new File(url));
        }else{
            rc = picasso.load(setImageResolution(url, 300, 300));
        }
        rc.fit().centerCrop().into(view);
    }

    private static String setImageResolution(String url, int x, int y) {
        if(url.contains("%dx%d")){
            return String.format(url, x, y);
        } else {
            return url;
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
