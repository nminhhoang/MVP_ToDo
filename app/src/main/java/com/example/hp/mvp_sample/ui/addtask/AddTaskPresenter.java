package com.example.hp.mvp_sample.ui.addtask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.mvp_sample.MainActivity;
import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.Task.TaskActivity;
import com.example.hp.mvp_sample.ui.base.BaseActivity;
import com.example.hp.mvp_sample.ui.base.BaseView;
import com.example.hp.mvp_sample.ui.home.HomeContract;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hp.mvp_sample.R.id.txtContent;
import static com.example.hp.mvp_sample.R.id.txtTitle;

/**
 * Created by hp on 10-Dec-16.
 */


public class AddTaskPresenter implements AddTaskContract.Presenter {
    private AddTaskContract.View mView;
    private  DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void setView(AddTaskContract.View view) {
        mView = view;
    }

    @Override
    public void AddTask(String mtxtTitle, String mtxtContent) {
        if (!mtxtTitle.matches("")) {

            Task tsk = new Task(mtxtTitle.toString(), mtxtContent.toString());

            mData.child(tsk.getId()).setValue(tsk, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        mView.AddComplete(true);
                    } else {
                        mView.AddComplete(false);
                    }
                }
            });

        }
        else{
            mView.EmptyTitle();
        }
    }

}