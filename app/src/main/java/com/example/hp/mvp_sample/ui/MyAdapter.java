package com.example.hp.mvp_sample.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hp.mvp_sample.R;
import com.example.hp.mvp_sample.Task;
import com.example.hp.mvp_sample.ui.Task.TaskActivity;
import com.example.hp.mvp_sample.ui.addtask.AddTaskActivity;
import com.example.hp.mvp_sample.ui.detailtask.DetailTaskActivity;
import com.example.hp.mvp_sample.ui.detailtask.DetailTaskContract;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter<Task> {


    private final Context context;
    private final ArrayList<Task> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Task> itemsArrayList) {

        super(context, R.layout.task_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.task_item, parent, false);

//        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.tskTitle);
        CheckBox cbComplete = (CheckBox) rowView.findViewById(R.id.cbComplete);
        LinearLayout btnRow = (LinearLayout) rowView.findViewById(R.id.btnRow);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getTitle());
        cbComplete.setChecked(itemsArrayList.get(position).getComplete());

        final Task tsk = new Task(itemsArrayList.get(position));

        cbComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tsk.changeComplete();
                DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
                mData.child(tsk.getId()).setValue(tsk, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError ==  null){
                            Toast.makeText(getContext(), "Lưu thành công!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Lưu thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ad = new Intent(getContext(),DetailTaskActivity.class);
                ad.putExtra("id",tsk.getId());
                context.startActivity(ad);

            }
        });

        // 5. return rowView
        return rowView;
    }
}