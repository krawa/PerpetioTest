package com.krawa.perpetiotest.ui.adapter;

import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.presentation.presenter.NewsFeedPresenter;

public class NewsFeedListAdapter extends BaseEndlessListAdapter<News>{

    public NewsFeedListAdapter(NewsFeedPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int compare(News item1, News item2) {
        return (int) (item2.getUpdate() - item1.getUpdate());
    }

    @Override
    protected boolean areContentsTheSame(News oldItem, News newItem) {
        return oldItem.getSubtitle().equals(newItem.getSubtitle());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.news_list_item;
    }


}
