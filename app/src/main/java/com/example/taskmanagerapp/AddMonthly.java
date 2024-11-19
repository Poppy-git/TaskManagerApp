package com.example.taskmanagerapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

public class AddMonthly extends AppCompatActivity {

    EditText txtTitle, txtDescription;
    TextView txtEnterDate;
    ImageButton btnclock;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_daily);

        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtEnterDate = findViewById(R.id.txtEnterTime);
        btnclock = findViewById(R.id.btnClock);
        btnsave = findViewById(R.id.btnsave);

        btnclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        btnsave.setOnClickListener(v-> {
            String taskTitle =txtTitle.getText().toString();
            String taskDescription = txtDescription.getText().toString();
            String taskTime = txtEnterDate.getText().toString();


            DatabaseHelper dh = new DatabaseHelper(AddMonthly.this);
            Task task = new Task(taskTitle, taskDescription, taskTime);
            dh.createTask(task);

            Intent i = new Intent(AddMonthly.this, AddEdit.class);
            startActivity(i);
            finish();
        });
    }

    private void openDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtEnterDate.setText(String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(dayOfMonth)+".");
            }
        },2024,0, 15);
        dialog.show();
    }
}