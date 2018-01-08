package com.lujainalfalaileh.bloodbank;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class UnderAge extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_age);
Toolbar toolbar=findViewById(R.id.toolb);
setSupportActionBar(toolbar);
getSupportActionBar().setTitle("Under Age");


    }







    public void gclick(View view) {

            Intent guest=new Intent(UnderAge.this,App_intro.class);
            startActivity(guest);

}}
