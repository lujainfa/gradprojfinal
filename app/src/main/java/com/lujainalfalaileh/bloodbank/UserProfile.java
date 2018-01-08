package com.lujainalfalaileh.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import android.widget.Button;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;





public class UserProfile extends AppCompatActivity implements View.OnClickListener{


    private DatabaseReference myDatabaseReference;




    @Override

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User's Answered Questions");
        String currentUser=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Button Available;
        Available=findViewById(R.id.Available);
        Available.setOnClickListener(this);
        final TextView text11,text22,text33,text44,text55,text66;
        text11=  findViewById(R.id.text11);
        text22=  findViewById(R.id.text22);
        text33=  findViewById(R.id.text33);
        text44 = findViewById(R.id.text44);
        text55=  findViewById(R.id.text55);
        text66=  findViewById(R.id.text66);

       final TextView tGender,tAge,tBlood,tName;
        tGender = findViewById(R.id.tGender);
        tAge = findViewById(R.id.Age);
        tBlood=findViewById(R.id.tBlood);
        tName=findViewById(R.id.Name);




        myDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Users");


        myDatabaseReference.child(currentUser).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) throws NullPointerException {
                Users info=dataSnapshot.getValue(Users.class);
               String name= info.getName();
                String age= info.getAge();
                String gender=info.getGender();
                String blood=info.getBlood();
                tGender.setText(gender);
                tAge.setText(age);
                tBlood.setText(blood);
                tName.setText(name);
                text11.setText(info.getText1());
                text22.setText(info.getText2());
                text33.setText(info.getText3());
                text44.setText(info.getText4());
                text55.setText(info.getText5());
                text66.setText(info.getText6());





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Failed to read value",databaseError.toException());

            }
        });

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i==R.id.Available){

            Intent ee=new Intent(UserProfile.this, ShowBloodBanks.class);
            startActivity(ee);
            UserProfile.this.finish();

        }
    }
}
