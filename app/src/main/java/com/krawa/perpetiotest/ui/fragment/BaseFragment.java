package com.krawa.perpetiotest.ui.fragment;

import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.krawa.perpetiotest.R;
import com.krawa.perpetiotest.presentation.view.BaseFragmentView;

public class BaseFragment extends MvpAppCompatFragment implements BaseFragmentView {

    private static final String TAG = "BaseFragment";

    public BaseFragment(){}

    @Override
    public void setTitle(final int resId) {
        setTitle(getString(resId));
    }

    @Override
    public void setTitle(final String title) {
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(title);
            }
        });
    }

}
