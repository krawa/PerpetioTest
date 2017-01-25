package com.krawa.perpetiotest.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BaseFragmentView extends MvpView {
    void setTitle(int resId);
    void setTitle(String title);
}
