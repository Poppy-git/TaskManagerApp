/*
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

        btnNewTask = findViewById(R.id.btnNewTask);
        RecyclerView rvTask = findViewById(R.id.rvTask);


        rvTask.setScrollbarFadingEnabled(false);

        //Button for new task
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



*/



package com.example.taskmanagerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.entity.Task;
import com.example.taskmanagerapp.helper.DatabaseHelper;
import com.example.taskmanagerapp.helper.TaskAdapter;

import java.util.ArrayList;

public class AddEdit extends AppCompatActivity {

    Button btnNewTask, btnHome;
    TextView txtListTitle; // TextView for the title
    RecyclerView rvTask;

    ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit);

        // Initialize views
        btnNewTask = findViewById(R.id.btnNewTask);
        btnHome = findViewById(R.id.btnHome);
        txtListTitle = findViewById(R.id.txtListTitle);
        rvTask = findViewById(R.id.rvTask);

        rvTask.setScrollbarFadingEnabled(false);

        // Set title based on intent data
        String taskTitle = getIntent().getStringExtra("taskTitle");
        if (taskTitle != null) {
            txtListTitle.setText(taskTitle);
        }

        // Button to create a new task
        btnNewTask.setOnClickListener(v -> {
            Intent i = new Intent(AddEdit.this, AddDaily.class);
            startActivity(i);
            finish();
        });

        // Button to go back to home screen
        btnHome.setOnClickListener(v -> {
            Intent i = new Intent(AddEdit.this, DisplayData.class);
            startActivity(i);
            finish();
        });

        // Load tasks into the RecyclerView
        loadTasks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskList.clear(); // Clear the list before reloading tasks
        loadTasks();
    }

    private void loadTasks() {
        DatabaseHelper dh = new DatabaseHelper(AddEdit.this);
        Cursor cur = dh.readAllTask();

        if (cur.getCount() == 0) {
            // No data found, optionally show a placeholder message
        } else {
            while (cur.moveToNext()) {
                Task task = new Task(
                        cur.getString(1), // Task Title
                        cur.getString(2), // Task Description
                        "Due at " + cur.getString(3) // Task Time
                );
                taskList.add(task);
            }
        }

        // Set up the RecyclerView with the TaskAdapter
        TaskAdapter taskAdapter = new TaskAdapter(AddEdit.this, taskList);
        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(AddEdit.this));
    }
}
