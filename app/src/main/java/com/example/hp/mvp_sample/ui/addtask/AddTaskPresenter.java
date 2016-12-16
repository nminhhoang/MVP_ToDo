package com.example.hp.mvp_sample.ui.addtask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.hp.mvp_sample.MainActivity;
import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.ui.Task.TaskActivity;
import com.example.hp.mvp_sample.ui.base.BaseActivity;
import com.example.hp.mvp_sample.ui.base.BaseView;
import com.example.hp.mvp_sample.ui.home.HomeContract;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hp.mvp_sample.R.id.txtContent;
import static com.example.hp.mvp_sample.R.id.txtTitle;

/**
 * Created by hp on 10-Dec-16.
 */


public class AddTaskPresenter implements AddTaskContract.Presenter {
    private AddTaskContract.View mView;

    @Override
    public void setView(AddTaskContract.View view) {

    }
}