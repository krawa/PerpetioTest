package com.krawa.perpetiotest;

import com.arellomobile.mvp.MvpApplication;
import com.krawa.perpetiotest.di.component.AppComponent;
import com.krawa.perpetiotest.di.component.DaggerAppComponent;
import com.krawa.perpetiotest.di.module.AppModule;
import com.krawa.perpetiotest.di.module.NavigationModule;
import com.krawa.perpetiotest.di.module.NetModule;

public class App extends MvpApplication {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .navigationModule(new NavigationModule())
                .build();
    }

}
