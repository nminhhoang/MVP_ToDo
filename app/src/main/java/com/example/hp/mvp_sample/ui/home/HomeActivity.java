package com.example.hp.mvp_sample.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp on 10-Dec-16.
 */

public class HomeActivity extends BaseActivity implements HomeContract.View{
    @BindView(R.id.increase)
    protected Button mBtnIncrease;
    private HomePresenter mPresenter;

    public static Intent newIntent(Context context){
        return new Intent(context,HomeActivity.class);
    }

    @Override
    protected void initializePresenter() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mPresenter = new HomePresenter();
        mPresenter.setView(this);

        mBtnIncrease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mPresenter.increaseValue();
            }
        });
    }

    @Override
    public void updateValue(String value) {
        mBtnIncrease.setText(value);
    }
}
