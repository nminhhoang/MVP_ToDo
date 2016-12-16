package com.example.hp.mvp_sample.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hp on 10-Dec-16.
 */

public abstract class BaseActivity extends AppCompatActivity{
    protected  abstract void initializePresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initializePresenter();
    }
}
