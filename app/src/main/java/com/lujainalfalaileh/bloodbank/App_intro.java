package com.lujainalfalaileh.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class App_intro extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Welcome To Blood Bank");


        final Button  bAdvice,gnear;
        bAdvice = findViewById(R.id.bAdvice);
        gnear=findViewById(R.id.gnear);
        bAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent advice = new Intent(App_intro.this, Advice.class);
                startActivity(advice);
            }
        });

        gnear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kkl = new Intent(App_intro.this, MapsActivity.class);
                startActivity(kkl);
            }
        });
    }
}











