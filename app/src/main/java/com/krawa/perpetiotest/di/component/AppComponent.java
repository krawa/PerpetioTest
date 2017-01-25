package com.krawa.perpetiotest.di.component;

import com.krawa.perpetiotest.di.module.AppModule;
import com.krawa.perpetiotest.di.module.NavigationModule;
import com.krawa.perpetiotest.di.module.NetModule;
import com.krawa.perpetiotest.di.scope.PerApplication;
import com.krawa.perpetiotest.presentation.presenter.ArticlePresenter;
import com.krawa.perpetiotest.presentation.presenter.NewsFeedPresenter;
import com.krawa.perpetiotest.ui.activity.MainActivity;

import dagger.Component;

@Component(modules = {AppModule.class, NetModule.class, NavigationModule.class})
@PerApplication
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(NewsFeedPresenter newsFeedPresenter);

    void inject(ArticlePresenter articlePresenter);
}
