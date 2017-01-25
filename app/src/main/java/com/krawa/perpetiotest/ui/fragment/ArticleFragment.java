package com.krawa.perpetiotest.ui.fragment;

import android.os.Bundle;

public class ArticleFragment extends BaseFragment {

    private static final String EXTRA_ARTICLE_ID = "articleId";

    public static ArticleFragment newInstance(String articleId) {

        Bundle args = new Bundle();
        args.putString(EXTRA_ARTICLE_ID, articleId);

        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
