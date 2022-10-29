package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExerciseWords2 extends AppCompatActivity {
    TextView text2;
    Button buttonw2_1, buttonw2_2, buttonw2_3, buttonw2_4;
    int index=0;
    PointManger pon;
    int count=0;
    String [] m2;
    String [] m2_1;
    String [] m2_2;
    int re=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_words2);
        re=(int) getIntent().getSerializableExtra("id");
        text2= findViewById(R.id.textVieww2);
        buttonw2_1= findViewById((R.id.buttonw2_1));
        buttonw2_2= findViewById((R.id.button2w2_2));
        buttonw2_3= findViewById(R.id.button3w2_3);
        buttonw2_4= findViewById(R.id.button5w2_4);
        m2=(String[]) getIntent().getSerializableExtra("questionwords1");
        m2_1=(String[]) getIntent().getSerializableExtra("answerwords1");
        m2_2= (String[]) getIntent().getSerializableExtra("correctanswer1");
        LoadPoint1();
        NextQuestion();
    }
    public void NextQuestion(){
        if(index==m2.length-1){
            pon.p[(re-1)*2+1]= count;
            SaveCount();
            super.onBackPressed();
        }
        else{
            text2.setText(m2[index]);
            buttonw2_1.setText(m2_1[index*4]);
            buttonw2_2.setText(m2_1[index*4+1]);
            buttonw2_3.setText((m2_1[index*4+2]));
            buttonw2_4.setText((m2_1[index*4+3]));
        }
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
            Log.e("load string",text);
            pon= buld.fromJson(text,PointManger.class);
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
    public void SaveCount(){

        FileOutputStream fos = null;

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson buld = builder.create();
            fos = openFileOutput("Point.json", MODE_PRIVATE);
            fos.write(buld.toJson(pon).getBytes());
            Log.e("save string",buld.toJson(pon));

        }
        catch(IOException ex) {
            Log.e("save","error");
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                Log.e("save","error");
            }
        }
    }
    public  void onClick_id1(View view){
        if(buttonw2_1.getText().toString().equals(m2_2[index])){
            count++;
        }
       index++;
        NextQuestion();
    }
    public  void onClick_id2(View view){
        if(buttonw2_2.getText().toString().equals(m2_2[index])){
            count++;

        }
        index++;
        NextQuestion();
    }
    public  void onClick_id3(View view){
        if(buttonw2_3.getText().toString().equals(m2_2[index])){
            count++;
        }
        index++;
        NextQuestion();
    }
    public  void onClick_id4(View view){
        if(buttonw2_4.getText().toString().equals(m2_2[index])){
            count++;
        }
        index++;
        NextQuestion();
    }
}