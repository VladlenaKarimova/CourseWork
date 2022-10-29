package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Theory1 extends AppCompatActivity {
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory1);
        text1= findViewById(R.id.textViewTheory1);
        String s=(String) getIntent().getSerializableExtra("Rules");
        text1.setText(s);
    }
}