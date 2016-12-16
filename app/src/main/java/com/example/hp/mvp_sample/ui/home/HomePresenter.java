package com.example.hp.mvp_sample.ui.home;

/**
 * Created by hp on 10-Dec-16.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    private int mValue;

    @Override
    public void increaseValue() {
        mValue++;
        mView.updateValue(String.valueOf(mValue));
    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

}
