package com.example.hp.mvp_sample.ui.detailtask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.mvp_sample.R;

import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.Task.TaskActivity;

import com.example.hp.mvp_sample.ui.base.BaseActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import butterknife.BindView;
import butterknife.ButterKnife;

import static android.webkit.WebSettings.PluginState.ON;
import static java.security.AccessController.getContext;

public class DetailTaskActivity extends BaseActivity implements DetailTaskContract.View{


    @BindView(R.id.cbComplete)
    protected CheckBox cbComplete;
    @BindView(R.id.txtContent)
    protected TextView txtContent;
    @BindView(R.id.txtTitle)
    protected TextView txtTitle;
    @BindView(R.id.btnDelete)
    protected ImageButton btnDelete;
    @BindView(R.id.btnBack)
    protected ImageButton btnBack;

//    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    public Task tskDetail;
    private DetailTaskPresenter mPresenter;

    public static Intent newIntent(Context context){
        return new Intent(context,DetailTaskActivity.class);
    }

    @Override
    protected void initializePresenter() {

    }

    public void ReturnHome(){
        startActivity(TaskActivity.newIntent(getApplicationContext()));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        ButterKnife.bind(this);

        mPresenter = new DetailTaskPresenter();
        mPresenter.setView(this);

        Bundle bd = getIntent().getExtras();
        String id = bd.getString("id");
        mPresenter.GetTaskDetail(id);

    }

    @Override
    public void CheckUpdateComplete(boolean result) {
        if (result) {
            Toast.makeText(DetailTaskActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DetailTaskActivity.this, "Lưu thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void CheckDeleteComplete(boolean result) {
        if (result) {
            Toast.makeText(DetailTaskActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            ReturnHome();
        } else {
            Toast.makeText(DetailTaskActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void UpdateDetailTask(final Task tsk2) {
        if(tsk2!= null) {
            cbComplete.setChecked(tsk2.getComplete());
            txtContent.setText(tsk2.getContent());
            txtTitle.setText(tsk2.getTitle());

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.DeleteTask(tsk2.getId());
                }
            });

            cbComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.UpdateComplete(tsk2);
                }
            });
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnHome();
            }
        });
    }
}
