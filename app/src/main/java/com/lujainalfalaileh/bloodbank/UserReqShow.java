package com.lujainalfalaileh.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserReqShow extends AppCompatActivity {
    DatabaseReference myDatabaseReference;
     String bankK,userK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_req_show);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User's Answered Form");
        final TextView text11,text22,text33,text44,text55,text66;
        text11=findViewById(R.id.text11);
        text22=findViewById(R.id.text22);
        text33=findViewById(R.id.text33);
        text44=findViewById(R.id.text44);
        text55=findViewById(R.id.text55);
        text66=findViewById(R.id.text66);
        myDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Users");


        Bundle extras=getIntent().getExtras();
        bankK=extras.getString("bankK");
        userK=extras.getString("userK");

        Button bResponse;
        bResponse=findViewById(R.id.bResponse);
        bResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent intent =new  Intent(UserReqShow.this,ReqResponse.class);
                    intent.putExtra("userK",userK);
                    intent.putExtra("bankK",bankK);
                    startActivity(intent);

                }

            }
        });



        myDatabaseReference.child(userK).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) throws NullPointerException {
                Users info=dataSnapshot.getValue(Users.class);


                assert info != null;
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

}
