package com.krawa.perpetiotest.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.presentation.presenter.BaseListFragmentPresenter;
import com.krawa.perpetiotest.presentation.presenter.NewsFeedPresenter;
import com.krawa.perpetiotest.presentation.view.NewsFeedView;
import com.krawa.perpetiotest.ui.adapter.NewsFeedListAdapter;

import java.util.List;

public class NewsFeedFragment extends BaseListFragment<News> implements NewsFeedView {

    public static final String TAG = "NewsFeedFragment";

    @InjectPresenter
    NewsFeedPresenter presenter;
    private NewsFeedListAdapter adapter;

    public NewsFeedFragment(){}

    @Override
    protected void onSetupList(RecyclerView list) {
        super.onSetupList(list);
        list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected BaseListFragmentPresenter providePresenter() {
        return presenter;
    }

    @Override
    protected RecyclerView.Adapter provideListAdapter() {
        adapter = new NewsFeedListAdapter(presenter);
        return adapter;
    }

    @Override
    public void updateList(List<News> items, boolean hasMore, boolean clear) {
        adapter.addAll(items, hasMore, clear);
    }
}
