package com.krawa.perpetiotest.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.databinding.FragmentArticleBinding;
import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.presentation.presenter.ArticlePresenter;
import com.krawa.perpetiotest.presentation.view.ArticleView;

public class ArticleFragment extends BaseFragment implements ArticleView {

    private static final String EXTRA_NEWS = "news";

    @InjectPresenter
    ArticlePresenter presenter;

    private FragmentArticleBinding binding;

    public static ArticleFragment newInstance(News news) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_NEWS, news);

        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.init((News) getArguments().getSerializable(EXTRA_NEWS));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false);
        return binding.getRoot();
    }

    @Override
    public void bindArticle(News news) {
        binding.setNews(news);
    }
}
