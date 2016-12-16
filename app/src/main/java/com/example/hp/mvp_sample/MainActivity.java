package com.example.hp.mvp_sample;

import android.os.Bundle;

import com.example.hp.mvp_sample.ui.Task.TaskActivity;
import com.example.hp.mvp_sample.ui.base.BaseActivity;
import com.example.hp.mvp_sample.ui.home.HomeActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(TaskActivity.newIntent(this));
        finish();
    }

}
