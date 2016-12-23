package com.example.hp.mvp_sample.ui.addtask;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.mvp_sample.MainActivity;
import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.Task.TaskActivity;
import com.example.hp.mvp_sample.ui.base.BaseActivity;
import com.example.hp.mvp_sample.ui.home.HomeActivity;
import com.example.hp.mvp_sample.ui.home.HomePresenter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hp.mvp_sample.R.id.txtContent;
import static com.example.hp.mvp_sample.R.id.txtTitle;

public class AddTaskActivity extends BaseActivity implements AddTaskContract.View  {
    @BindView(R.id.btnAddTask)
    protected FloatingActionButton mBtnAddTask;
    @BindView(R.id.txtTitle)
    protected EditText mtxtTitle;
    @BindView(R.id.txtContent)
    protected EditText mtxtContent;
    @BindView(R.id.btnBack)
    protected ImageButton btnBack;

    private AddTaskPresenter mPresenter;
    private  DatabaseReference mData;

    public static Intent newIntent(Context context){
        return new Intent(context,AddTaskActivity.class);
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task2);
        ButterKnife.bind(this);

        mPresenter = new AddTaskPresenter();
        mPresenter.setView(this);

        mData = FirebaseDatabase.getInstance().getReference();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnHome();
            }
        });

        mBtnAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mPresenter.AddTask(mtxtTitle.getText().toString(),mtxtContent.getText().toString());
            }
        });

    }

    public void ReturnHome(){
        startActivity(TaskActivity.newIntent(getApplicationContext()));
        finish();
    }

    @Override
    public void EmptyTitle() {
        Toast.makeText(AddTaskActivity.this, "Tiêu đề rỗng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void AddComplete(boolean rs) {
        if (rs) {
            Toast.makeText(AddTaskActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            ReturnHome();
        } else {
            Toast.makeText(AddTaskActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}
