package com.example.hp.mvp_sample.ui.Task;

import android.widget.ListView;

import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.MyAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.hp.mvp_sample.ui.Task.TaskActivity.adapter;
import static com.example.hp.mvp_sample.ui.Task.TaskActivity.items;

/**
 * Created by hp on 23-Dec-16.
 */

public class TaskPresenter implements TaskContract.Presenter {
    private  TaskContract.View mView;

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public void setView(TaskContract.View view) {
        mView = view;
    }

    @Override
    public ArrayList<Task> generateData(){
        items = new ArrayList<Task>();

        mData.orderByChild("title").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Task task = dataSnapshot.getValue(Task.class);
                items.add(task);
                adapter.notifyDataSetChanged();
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

        return items;
    }
}
