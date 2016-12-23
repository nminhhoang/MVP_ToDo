package com.example.hp.mvp_sample.ui.addtask;

import com.example.hp.mvp_sample.ui.base.BasePresenter;
import com.example.hp.mvp_sample.ui.base.BaseView;

/**
 * Created by hp on 10-Dec-16.
 */

public interface  AddTaskContract {
    interface View extends BaseView {
        void EmptyTitle();
        void AddComplete(boolean rs);
    }

    interface  Presenter extends BasePresenter<View> {
        void AddTask(String mtxtTitle, String mtxtContent);
    }
}

