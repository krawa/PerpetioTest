package com.krawa.perpetiotest.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    public static String TAG = "EndlessRecyclerOnScrollListener";

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 2; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    private ScroolDirection dir;

    public enum ScroolDirection{
        DOWNWARD, UPWARD
    }

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this(linearLayoutManager, ScroolDirection.DOWNWARD);
    }

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, ScroolDirection dir) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.dir = dir;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount() - 1;
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (!loading) {
            if(dir == ScroolDirection.DOWNWARD && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)){
                loadMore();
            }else if(dir == ScroolDirection.UPWARD && firstVisibleItem <= visibleThreshold){
                loadMore();
            }

        }
    }

    public void setLoadingState(boolean isLoading){
        loading = isLoading;
    }

    private void loadMore() {
        current_page++;
        onLoadMore(current_page);
        loading = true;
    }

    public void reset(){
        previousTotal = 0;
        loading = true;
        current_page = 1;
    }

    //public abstract boolean hasMore();
    public abstract void onLoadMore(int current_page);
}

