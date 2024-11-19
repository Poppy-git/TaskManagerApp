package com.example.taskmanagerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.entity.Task;
import com.example.taskmanagerapp.helper.DatabaseHelper;
import com.example.taskmanagerapp.helper.TaskAdapter;

import java.util.ArrayList;

public class AddEdit extends AppCompatActivity {

    Button btnNewTask;
    RecyclerView rvTask;

    ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNewTask = findViewById(R.id.btnNewTask);
        RecyclerView rvTask = findViewById(R.id.rvTask);


        rvTask.setScrollbarFadingEnabled(false);

        btnNewTask.setOnClickListener( v-> {
            Intent i = new Intent(AddEdit.this, AddDaily.class);
            startActivity(i);
            finish();

        });


        DatabaseHelper dh = new DatabaseHelper(AddEdit.this);
        Cursor cur = dh.readAllTask();

        if(cur.getCount()==0) {
            //NO DATA
        } else {

            while (cur.moveToNext()) {
                Task t = new Task (cur.getString(1), cur.getString(2),"Due at " +  cur.getString(3) );

                taskList.add(t);
            }

            //Toast.makeText(this, taskList.size() + "", Toast.LENGTH_SHORT).show();
        }

        TaskAdapter taskAdapter = new TaskAdapter(AddEdit.this, taskList);
        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(AddEdit.this));

    }

    protected void onResume() {
        super.onResume();
        taskList.clear();

        DatabaseHelper dh = new DatabaseHelper(AddEdit.this);
        Cursor cur = dh.readAllTask();

        if(cur.getCount()==0) {
            //NO DATA
        } else {

            while (cur.moveToNext()) {
                Task t = new Task (cur.getString(1), cur.getString(2),"Due at " +  cur.getString(3) );

                taskList.add(t);
            }

            //Toast.makeText(this, taskList.size() + "", Toast.LENGTH_SHORT).show();
        }


    }
}