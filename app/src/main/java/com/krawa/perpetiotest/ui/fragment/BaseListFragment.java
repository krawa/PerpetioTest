package com.krawa.perpetiotest.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.databinding.FragmentListBinding;
import com.krawa.perpetiotest.presentation.presenter.BaseListFragmentPresenter;
import com.krawa.perpetiotest.presentation.view.BaseListFragmentView;
import com.krawa.perpetiotest.utils.EndlessRecyclerOnScrollListener;

public abstract class BaseListFragment<T> extends BaseFragment implements BaseListFragmentView<T>, SwipeRefreshLayout.OnRefreshListener {

    private FragmentListBinding binding;
    private EndlessRecyclerOnScrollListener loadMoreListener;

    public BaseListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        onSetupList(binding.list);
        return binding.getRoot();
    }

    protected void onSetupList(RecyclerView list) {
        LinearLayoutManager layoutManager = provideLayoutManager();
        list.setLayoutManager(layoutManager);

        loadMoreListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                providePresenter().loadMore(currentPage);
            }
        };
        list.addOnScrollListener(loadMoreListener);

        binding.swipeContainer.setOnRefreshListener(this);
        binding.swipeContainer.setColorSchemeResources(R.color.colorAccent);

        list.setAdapter(provideListAdapter());
    }

    protected LinearLayoutManager provideLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Override
    public void showListProgress(final boolean show) {
        binding.swipeContainer.post(new Runnable() {
            @Override
            public void run() {
                binding.swipeContainer.setRefreshing(show);
            }
        });
    }

    @Override
    public void setEmptyText(String text) {
        binding.tvEmptyText.setVisibility(binding.list.getAdapter() == null || binding.list.getAdapter().getItemCount() == getDeltaItemCount() ? View.VISIBLE : View.GONE);
        binding.tvEmptyText.setText(text != null ? text : getString(R.string.empty_list));
    }

    protected int getDeltaItemCount() {
        return 1;
    }

    @Override
    public void addItem(T item){}

    @Override
    public void removeItem(T item){}

    @Override
    public void updateItem(T item){}

    @Override
    public void setListPosition(int position) {
        ((LinearLayoutManager) binding.list.getLayoutManager()).scrollToPositionWithOffset(position, 0);
    }

    @Override
    public void onRefresh() {
        binding.tvEmptyText.setVisibility(View.GONE);
        providePresenter().reloadAll();
    }

    public RecyclerView getListView(){
        return binding.list;
    }

    public FragmentListBinding getBinding(){
        return binding;
    }

    protected abstract BaseListFragmentPresenter providePresenter();

    protected abstract RecyclerView.Adapter provideListAdapter();

}
