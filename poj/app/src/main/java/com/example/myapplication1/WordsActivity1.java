package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WordsActivity1 extends AppCompatActivity {
    TextView word1;
    TextView word1_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words1);
        word1= findViewById(R.id.textViewEngWords1);
        word1_1= findViewById(R.id.textViewRusWords1);
        String [] m1=(String[]) getIntent().getSerializableExtra("wordsEng");
        String [] m1_1=(String[]) getIntent().getSerializableExtra("wordsTrs");

        for ( int i=0;i< m1.length;i++)
        {
            word1.setText(word1.getText()+m1[i]+"\n");
            word1_1.setText(word1_1.getText()+m1_1[i]+"\n");
        }
    }
}