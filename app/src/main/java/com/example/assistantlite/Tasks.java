package com.example.assistantlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tasks extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        b1 =(Button) findViewById(R.id.charades);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                opentasks();
            }
        });
    }
    public void opentasks(){
        Intent intentnew = new Intent(this, charades.class);
        startActivity(intentnew);
    }
}