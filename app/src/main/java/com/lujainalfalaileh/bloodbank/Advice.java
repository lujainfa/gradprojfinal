package com.lujainalfalaileh.bloodbank;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class Advice extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advice);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donation Advice");


    }



    public void sclick(View view) {


        Intent sintent= new Intent(Advice.this, GoogleSignInActivity.class);
        startActivity(sintent);
    }
}
