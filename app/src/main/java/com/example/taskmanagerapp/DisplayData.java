package com.example.taskmanagerapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayData extends AppCompatActivity {

    final char[] value = {'D', 'W', 'M'}; //Daily, Weekly, Monthly
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //All buttons is papunta sa AddEdit
        //Depending kung ano pinindot na list, 'yun yung magiging text ng "txtListTitle" sa AddEdit
    }
}