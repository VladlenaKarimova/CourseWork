package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ExerciseWords1 extends AppCompatActivity {
    List<Question> questions;
    ImageView img;
    String answerCorrect;
    EditText edit_textanswer;
    int index=0;
    int count=0;
    PointManger pon;
    int re=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_words1);
        img=findViewById(R.id.imageView);
        edit_textanswer= findViewById(R.id.editTextTextAnswer);
        re=(int) getIntent().getSerializableExtra("id");
        LoadPoint1();

        String [] m= getResources().getStringArray(R.array.name_images);
        questions=new ArrayList<Question>() ;

        for(int i=(re)*14; i<(re)*14+14;i++)
        {
            questions.add(new Question(getResources().getIdentifier(m[i],"drawable", getPackageName()),m[i]));
        }
        SetnextQuestion();
        edit_textanswer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()==keyEvent.ACTION_DOWN&& (i==KeyEvent.KEYCODE_ENTER)) {
                    if  (questions.get(index).answer.equals(edit_textanswer.getText().toString()) ){
                        // true answer
                        questions.remove(index);
                        SetnextQuestion();
                        count++;
                    }
                    else {
                        questions.remove(index);
                        SetnextQuestion();
                    }
                }
                return  false;
            }

        });
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
    void SetnextQuestion (){
        if (questions.isEmpty()==false) {
            Random ra = new Random();
            index = ra.nextInt(questions.size());
            img.setImageResource(questions.get(index).image_id);
            answerCorrect = questions.get(index).answer;
            Log.e("next question",String.valueOf(count));
        }
        else {
            pon.p[(re-1)*2]= count;

            SaveCount();
            super.onBackPressed();

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
}



 class Question {
    public int image_id;
    public String answer;
    public Question(int image_id, String answer){
        this.image_id=image_id;
        this.answer=answer;

    }
}