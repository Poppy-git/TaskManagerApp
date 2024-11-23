
/*
package com.example.taskmanagerapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayData extends AppCompatActivity {

    final String[] placement = {"Daily", "Weekly", "Monthly"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //All buttons is papunta sa AddEdit
        //Depending kung ano pinindot na list, 'yun yung magiging text ng "txtListTitle" sa AddEdit
    }
}


 */




package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Buttons from activity_main.xml
        LinearLayout btnTodaysTasks = findViewById(R.id.btnTodaysTasks);
        LinearLayout btnWeeklyTasks = findViewById(R.id.btnWeeklyTasks);
        LinearLayout btnMonthlyTasks = findViewById(R.id.btnMonthlyTasks);

        // Navigate to AddEdit with specific titles
        btnTodaysTasks.setOnClickListener(v -> navigateToAddEdit("Today's Tasks"));
        btnWeeklyTasks.setOnClickListener(v -> navigateToAddEdit("Weekly Tasks"));
        btnMonthlyTasks.setOnClickListener(v -> navigateToAddEdit("Monthly Tasks"));
    }

    private void navigateToAddEdit(String title) {
        Intent intent = new Intent(DisplayData.this, AddEdit.class);
        intent.putExtra("taskTitle", title); // Pass the title to AddEdit
        startActivity(intent);
    }
}
