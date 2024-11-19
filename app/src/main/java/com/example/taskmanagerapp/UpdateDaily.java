package com.example.taskmanagerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.taskmanagerapp.entity.Task;
import com.example.taskmanagerapp.helper.DatabaseHelper;

public class UpdateDaily extends AppCompatActivity {

    Button btnUpdate;
    EditText txtTitleUp, txtDescriptionUp;
    TextView txtEnterTimeUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_daily);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        DatabaseHelper dh = new DatabaseHelper(UpdateDaily.this);
        Cursor cursor = dh.readTaskById(id);

        if (cursor.moveToFirst()) {
            txtTitleUp.setText(cursor.getString(1));
            txtDescriptionUp.setText(cursor.getString(2));
            txtEnterTimeUp.setText(cursor.getString(3));
        }

        btnUpdate.setOnClickListener(v -> {
            int ID = Integer.parseInt(i.getStringExtra("id"));

            String title = txtTitleUp.getText().toString();
            String description = txtDescriptionUp.getText().toString();
            String time = txtEnterTimeUp.getText().toString();

            Task t = new Task(ID, title, description, time);

            long result = dh.updateTaskbyId(t);

            if (result != -1) { // Corrected condition
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                getOnBackPressedDispatcher().onBackPressed(); // Go back to the previous activity
            } else {
                Toast.makeText(this, "Error Updating", Toast.LENGTH_SHORT).show();
            }
        });


    }
}