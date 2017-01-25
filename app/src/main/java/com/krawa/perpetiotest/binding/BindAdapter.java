package com.krawa.perpetiotest.binding;

import android.databinding.BindingAdapter;
import android.webkit.WebView;
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

    @BindingAdapter({"app:loadDataString"})
    public static void loadStringWebView(WebView webView, String text) {
        String html = "<html><body>" + text + "</body></html>";
        webView.loadData(html, "text/html; charset=utf-8", "UTF-8");
    }

}
