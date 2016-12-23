package com.example.hp.mvp_sample.ui.Task;

import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.MyAdapter;
import com.example.hp.mvp_sample.ui.base.BasePresenter;
import com.example.hp.mvp_sample.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by hp on 10-Dec-16.
 */

public interface TaskContract {
    interface View extends BaseView {
    }

    interface  Presenter extends BasePresenter<View> {
        ArrayList<Task> generateData();
    }
}
