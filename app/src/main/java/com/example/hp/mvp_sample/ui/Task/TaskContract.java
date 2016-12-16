package com.example.hp.mvp_sample.ui.Task;

import com.example.hp.mvp_sample.ui.base.BasePresenter;
import com.example.hp.mvp_sample.ui.base.BaseView;

/**
 * Created by hp on 10-Dec-16.
 */

public interface TaskContract {
    interface View extends BaseView {
        void updateValue(String value);
    }

    interface  Presenter extends BasePresenter<View> {
        void increaseValue();
    }
}
