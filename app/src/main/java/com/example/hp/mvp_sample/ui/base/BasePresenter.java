package com.example.hp.mvp_sample.ui.base;

/**
 * Created by hp on 10-Dec-16.
 */

public interface BasePresenter <T extends BaseView>{
     void setView(T view);
}
