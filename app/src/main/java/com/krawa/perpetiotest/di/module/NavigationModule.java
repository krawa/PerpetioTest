package com.krawa.perpetiotest.di.module;

import com.krawa.perpetiotest.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {

    @Provides
    @PerApplication
    public Cicerone provideCicerone() {
        return Cicerone.create();
    }

    @Provides
    @PerApplication
    public NavigatorHolder provideNavigatorHolder(Cicerone cicerone) {
        return cicerone.getNavigatorHolder();
    }

    @Provides
    @PerApplication
    public Router provideRouter(Cicerone cicerone) {
        return (Router) cicerone.getRouter();
    }

}
