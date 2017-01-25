package com.krawa.perpetiotest.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.krawa.perpetiotest.App;
import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.model.News;
import com.krawa.perpetiotest.ui.Screen;
import com.krawa.perpetiotest.ui.fragment.ArticleFragment;
import com.krawa.perpetiotest.ui.fragment.NewsFeedFragment;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Forward;

public class MainActivity extends AppCompatActivity {

    @Inject
    NavigatorHolder navigatorHolder;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey){
                case Screen.NEWS_FEED:
                    return new NewsFeedFragment();
                case Screen.ARTICLE:
                    return ArticleFragment.newInstance((News) data);
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            navigator.applyCommand(new Forward(Screen.NEWS_FEED, null));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

}
