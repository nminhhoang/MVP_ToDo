package com.example.hp.mvp_sample.ui.detailtask;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.Task.TaskActivity;
import com.example.hp.mvp_sample.ui.Task.TaskContract;
import com.example.hp.mvp_sample.ui.detailtask.DetailTaskContract.Presenter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hp.mvp_sample.R.id.btnDelete;
import static com.example.hp.mvp_sample.R.id.cbComplete;
import static com.example.hp.mvp_sample.R.id.txtContent;
import static com.example.hp.mvp_sample.R.id.txtTitle;

/**
 * Created by hp on 16-Dec-16.
 */

public class DetailTaskPresenter implements DetailTaskContract.Presenter {

    private DetailTaskContract.View mView;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void setView(DetailTaskContract.View view) {
        mView = view;
    }

    @Override
    public void GetTaskDetail(String id) {
        Query queryRef = mData.orderByChild("id").equalTo(id);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Task facts = dataSnapshot.getValue(Task.class);
                mView.UpdateDetailTask(facts);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void DeleteTask(String id) {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child(id).removeValue();
        mView.CheckDeleteComplete(true);
    }

    @Override
    public void UpdateComplete(Task tsk) {
        tsk.changeComplete();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child(tsk.getId()).setValue(tsk, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    mView.CheckUpdateComplete(true);
                } else {
                    mView.CheckUpdateComplete(false);
                }
            }
        });
    }
}
