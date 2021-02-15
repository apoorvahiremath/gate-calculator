package com.example.apoorva.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCalc(View v){
        Intent i = new Intent(this, Calc.class);
        this.startActivity(i);
    }

    public void openInstruction(View v){
        Intent i = new Intent(this, Instructions.class);
        this.startActivity(i);
    }

}
