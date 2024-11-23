package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DisplayData extends AppCompatActivity {

    final String[] taskPlacement = {"Daily", "Weekly", "Monthly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Buttons from activity_main.xml
        LinearLayout btnTodaysTasks = findViewById(R.id.btnTodaysTasks);
        LinearLayout btnWeeklyTasks = findViewById(R.id.btnWeeklyTasks);
        LinearLayout btnMonthlyTasks = findViewById(R.id.btnMonthlyTasks);

        // Navigate to AddEdit with specific titles and taskPlacement
        btnTodaysTasks.setOnClickListener(v -> navigateToAddEdit("Today's Tasks", taskPlacement[0]));
        btnWeeklyTasks.setOnClickListener(v -> navigateToAddEdit("Weekly Tasks", taskPlacement[1]));
        btnMonthlyTasks.setOnClickListener(v -> navigateToAddEdit("Monthly Tasks", taskPlacement[2]));
    }

    private void navigateToAddEdit(String title, String placement) {
        Intent intent = new Intent(DisplayData.this, AddEdit.class);
        intent.putExtra("taskTitle", title); // Pass the title to AddEdit
        intent.putExtra("taskPlacement", placement); // Pass the task placement
        startActivity(intent);
    }
}
