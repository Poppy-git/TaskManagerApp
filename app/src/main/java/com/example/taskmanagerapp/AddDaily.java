package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.taskmanagerapp.entity.Task;
import com.example.taskmanagerapp.helper.DatabaseHelper;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.MessageFormat;
import java.util.Locale;

public class AddDaily extends AppCompatActivity {


    EditText txtTitle, txtDescription;
    TextView txtEnterTime;
    ImageButton btnclock;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_daily);

        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtEnterTime = findViewById(R.id.txtEnterTime);
        btnclock = findViewById(R.id.btnClock);
        btnsave = findViewById(R.id.btnUpdate);

        //Time input
        btnclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                        .setTitleText("Pick Time")
                        .build();
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        txtEnterTime.setText(MessageFormat.format("{0}:{1}", String.format(Locale.getDefault(), "%02d",timePicker.getHour()),String.format(Locale.getDefault(),"%02d",timePicker.getMinute())));
                    }
                });
                timePicker.show(getSupportFragmentManager(),"tag");
            }
        });

        //Save button to save user inputted data from fields
        btnsave.setOnClickListener(v-> {
            String taskTitle =txtTitle.getText().toString();
            String taskDescription = txtDescription.getText().toString();
            String taskTime = txtEnterTime.getText().toString();

            //Passes data from the fields to the database
            DatabaseHelper dh = new DatabaseHelper(AddDaily.this);
            Task task = new Task(taskTitle, taskDescription, taskTime);
            dh.createTask(task);

            //Exits current activity (AddMonthly) to previous activity (AddEdit)
            Intent i = new Intent(AddDaily.this, AddEdit.class);
            startActivity(i);
            finish();
        });
    }
}