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

    public static MyAdapter adapter;
    public static ArrayList<Task> items;
    private TaskPresenter mPresenter;

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

        mPresenter = new TaskPresenter();
        mPresenter.setView(this);

        adapter = new MyAdapter(this, mPresenter.generateData());
        mlvTask.setAdapter(adapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AddTaskActivity.newIntent(getApplicationContext()));
                finish();
            }
        });
    }

}

