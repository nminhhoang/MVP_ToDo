package com.example.hp.mvp_sample.ui.Task;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.MyAdapter;
import com.example.hp.mvp_sample.ui.addtask.AddTaskActivity;
import com.example.hp.mvp_sample.ui.base.BaseActivity;
import com.example.hp.mvp_sample.ui.home.HomeActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;
import static android.R.attr.value;
import static android.R.id.list;

public class TaskActivity extends BaseActivity implements TaskContract.View {

    @BindView(R.id.btnAddTask)
    protected FloatingActionButton btnAddTask;
    @BindView(R.id.lvTask)
    protected ListView mlvTask;

    ArrayList<Task> items = new ArrayList<Task>();
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public MyAdapter adapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, TaskActivity.class);
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);


        adapter = new MyAdapter(this, generateData());

        mlvTask.setAdapter(adapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setContentView(R.layout.activity_home);
                startActivity(AddTaskActivity.newIntent(getApplicationContext()));
                finish();
            }
        });
    }

    private ArrayList<Task> generateData(){
        mData.orderByChild("title").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Task task = dataSnapshot.getValue(Task.class);
    //                    Toast.makeText(TaskActivity.this, task.getTitle() +"-"+task.getContent(),Toast.LENGTH_SHORT).show();
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
    @Override
    public void updateValue(String value) {

    }


}

