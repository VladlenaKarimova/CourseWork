package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooseActivity extends AppCompatActivity {
    ListView param1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        param1= findViewById(R.id.listview1);
        String [] array = {"Глаголы to be","b","c"};
        ArrayAdapter <String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        param1.setAdapter(adapter);
        param1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ChooseActivity.this,SelectionofTaskActivity.class);
                intent.putExtra("id",i+1);
                startActivity(intent);

            }
        });
    }

}