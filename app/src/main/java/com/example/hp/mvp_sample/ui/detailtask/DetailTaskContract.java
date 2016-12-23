package com.example.hp.mvp_sample.ui.detailtask;

import android.os.Bundle;

import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.base.BasePresenter;
import com.example.hp.mvp_sample.ui.base.BaseView;

import static android.R.attr.id;

/**
 * Created by hp on 16-Dec-16.
 */


public interface DetailTaskContract {
    interface View extends BaseView {
        void UpdateDetailTask(final Task tsk2);
        void CheckUpdateComplete(boolean result);
        void CheckDeleteComplete(boolean result);
    }

    interface Presenter extends BasePresenter<View> {
        void GetTaskDetail(String id);
        void DeleteTask(String id);
        void UpdateComplete(Task tsk);
    }
}