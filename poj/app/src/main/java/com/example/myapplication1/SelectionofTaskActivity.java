package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SelectionofTaskActivity extends AppCompatActivity {
    ExObject ex1;
    PointManger pon;
    TextView tx1;
    TextView tx2;
    int re=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionof_task);
        String rea=null;
        re=(int) getIntent().getSerializableExtra("id");

        try  {
            InputStream way1= this.getAssets().open("Ex"+re+".json");
                int size= way1.available();
                byte[] arrays= new byte[size];
                way1.read(arrays);
                way1.close();
                rea= new String(arrays,"UTF-8");
        }
        catch(IOException e) {
            e.printStackTrace();
            Log.e("selection","error");
        }
        GsonBuilder builder= new GsonBuilder();// дополнительная библиотека
        Gson buld= builder.create();

        ex1= buld.fromJson(rea,ExObject.class);

        tx1= findViewById(R.id.textViewSelection3);
        tx2= findViewById(R.id.textViewSelection4);
        tx1.setText(String.valueOf(0));
        tx2.setText(String.valueOf(0));

        LoadPoint1();


    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadPoint1();
    }

    public void LoadPoint1(){
        GsonBuilder builder= new GsonBuilder();
        Gson buld= builder.create();
        FileInputStream fin = null;
        try {
            fin = openFileInput("Point.json");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            pon= buld.fromJson(text,PointManger.class);
            tx1.setText(String.valueOf(pon.p[(re-1)*2]));
            tx2.setText(String.valueOf(pon.p[(re-1)*2+1]));
        }
        catch(IOException ex) {

            pon = new PointManger();
            pon.p= new int [10];
            Log.e("Load","error");
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Log.e("Load","error");
            }
        }
    }
    public void onClickTheory(View view){
        Intent intent=new Intent(SelectionofTaskActivity.this,Theory1.class);
        intent.putExtra("Rules",ex1.rule);
        startActivity(intent);



    }

    public void onClickWords(View view){
        Intent intent=new Intent(SelectionofTaskActivity.this,WordsActivity1.class);
        intent.putExtra("wordsEng",ex1.wordsEng);

        intent.putExtra("wordsTrs",ex1.wordsTrs);
        startActivity(intent);
    }

    public void onClickQuestion(View view){
        Intent intent=new Intent(SelectionofTaskActivity.this, ExerciseWords1.class);
        intent.putExtra("id",ex1.id);
        startActivity(intent);
    }

    public void onClickTraining(View view){
        Intent intent=new Intent(SelectionofTaskActivity.this,ExerciseWords2.class);
        intent.putExtra("questionwords1",ex1.questionwords1);
        intent.putExtra("answerwords1",ex1.answerwords1);
        intent.putExtra("correctanswer1", ex1.correctanswer1);
        intent.putExtra("id",ex1.id);
        startActivity(intent);
    }
}
