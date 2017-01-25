package com.krawa.perpetiotest.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.krawa.perpetiotest.presentation.view.BaseListFragmentView;

@InjectViewState
public abstract class BaseListFragmentPresenter<View extends BaseListFragmentView> extends MvpPresenter<BaseListFragmentView> {

    public BaseListFragmentPresenter() {}

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showListProgress(true);
        reloadAll();
    }

    public void reloadAll(){
        getItems(0);
    }

    public void loadMore(int currentPage) {
        getItems(currentPage);
    }

    protected abstract void getItems(int page);
}
