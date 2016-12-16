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
    private DetailTaskPresenter mPresenter;

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

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnHome();
            }
        });
        Bundle bd = getIntent().getExtras();
        if (bd != null) {
            String id = bd.getString("id");
            Query queryRef = mData.orderByChild("id").equalTo(id);

            queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Task facts = dataSnapshot.getValue(Task.class);
                    final Task tsk = new Task(facts);

                    cbComplete.setChecked(facts.getComplete());
                    txtContent.setText(facts.getContent());
                    txtTitle.setText(facts.getTitle());
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
                            mData.child(tsk.getId()).removeValue();
                            ReturnHome();
                        }
                    });

                    cbComplete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tsk.changeComplete();
                            DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
                            mData.child(tsk.getId()).setValue(tsk, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if(databaseError ==  null){
                                        Toast.makeText(DetailTaskActivity.this, "Lưu thành công!",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(DetailTaskActivity.this, "Lưu thất bại!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
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
    }


}
