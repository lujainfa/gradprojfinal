package com.lujainalfalaileh.bloodbank;


import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MedicalForm2 extends AppCompatActivity {
    DatabaseReference myDatabaseReference;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_form2);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form");


        final String yes="yes";
        final String no="no";
        final String name,age,blood,gender;


        final TextView text1,text2,text3,text4,text5,text6 ;
        final Switch switch1, switch2, switch3, switch4, switch5, switch6;
        final SharedPreferences switchprefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = switchprefs.edit();


        myDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Users");

        Bundle extras= getIntent().getExtras();
        blood=extras.getString("blood");
        gender=extras.getString("gender");
        name=extras.getString("name");
        age=extras.getString("age");



        switch1 =  findViewById(R.id.switch1);
        text1=  findViewById(R.id.text1);




        switch1.setChecked(switchprefs.getBoolean("state1",true));
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text1.setText(yes);



                    editor.putBoolean("state1",true);

                    editor.commit();

                }else{
                    text1.setText(no);


                    editor.putBoolean("state1",false);



                    editor.commit();


                }


            }
        });


        switch2 =  findViewById(R.id.switch2);
       switch2.setChecked(switchprefs.getBoolean("state2",true));
        text2=  findViewById(R.id.text2);



        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text2.setText(yes);

                    editor.putBoolean("state2",true);
                    editor.commit();
                }else{
                    text2.setText(no);

                    editor.putBoolean("state2",false);//InputString: from the EditText
                    editor.commit();
                }

            }
        });



        switch3 =  findViewById(R.id.switch3);
        switch3.setChecked(switchprefs.getBoolean("state3",true));
        text3=  findViewById(R.id.text3);


        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                text3.setText(yes);

                editor.putBoolean("state3",true);
                editor.commit();
            }else{
                text3.setText(no);

                editor.putBoolean("state3",false);
                editor.commit();
            }

        }
    });



        switch4 =  findViewById(R.id.switch4);
        switch4.setChecked(switchprefs.getBoolean("state4",true));
        text4=  findViewById(R.id.text4);


        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text4.setText(yes);

                    editor.putBoolean("state4",true);//InputString: from the EditText
                    editor.commit();
                }else{
                    text4.setText(no);

                    editor.putBoolean("state4",false);
                    editor.commit();
                }

            }
        });


        switch5 =  findViewById(R.id.switch5);
        switch5.setChecked(switchprefs.getBoolean("state5",true));
        text5=  findViewById(R.id.text5);


        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text5.setText(yes);

                    editor.putBoolean("state5", true);//InputString: from the EditText
                    editor.commit();
                }else{
                    text5.setText(no);

                    editor.putBoolean("state5", false);//InputString: from the EditText
                    editor.commit();
                }

            }
        });
        switch6 =  findViewById(R.id.switch6);
        text6=  findViewById(R.id.text6);


        switch6.setChecked(switchprefs.getBoolean("state6",true));
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text6.setText(yes);
                    editor.putString("text6", (String) text6.getText()); //InputString: from the EditText
                    editor.putBoolean("state6",true);
                    editor.commit();
                }else{
                    text6.setText(no);
                    editor.putString("text6", (String) text6.getText()); //InputString: from the EditText
                    editor.putBoolean("state6",false);
                    editor.commit();
                }

            }
        });



        findViewById(R.id.bSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        if(text1.getText().equals("")|text2.getText().equals("")|text3.getText().equals("")|text3.getText().equals("")
                        |text4.getText().equals("")|text5.getText().equals("")|text6.getText().equals("")){
                            Toast.makeText(MedicalForm2.this, "Please check all your answers!", Toast.LENGTH_SHORT).show();
                        }else {

                            addPerson(name.trim(), age.trim(), gender, blood,
                                    text1.getText().toString().trim(), text2.getText().toString().trim(),
                                    text3.getText().toString().trim(), text4.getText().toString().trim(),
                                    text5.getText().toString().trim(), text6.getText().toString().trim());
                            Intent next_page = new Intent(MedicalForm2.this, UserProfile.class);
                            Toast.makeText(MedicalForm2.this,"Your answers have been saved successfully!",Toast.LENGTH_LONG).show();
                            startActivity(next_page);
                        }


            }
        });}


    public void addPerson(String name, String age, String gender, String blood,
                           String text1, String text2, String text3, String text4, String text5, String text6){
        @Nullable

        FirebaseUser iD = FirebaseAuth.getInstance().getCurrentUser();
        Users user = new Users(name, age, gender, blood,text1,text2,text3,text4,text5,text6);



        myDatabaseReference.child(iD.getUid()).setValue(user);



}







}




