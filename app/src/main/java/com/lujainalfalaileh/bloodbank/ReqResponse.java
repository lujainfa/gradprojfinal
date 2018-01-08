package com.lujainalfalaileh.bloodbank;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class ReqResponse extends AppCompatActivity {
    Button bAppointment, bDecline;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    String bankK, userK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_response);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Requests Response");


        Bundle extras = getIntent().getExtras();
        bankK = extras.getString("bankK");
        userK = extras.getString("userK");


        bAppointment = findViewById(R.id.bAppointment);
        bDecline = findViewById(R.id.bDecline);
        bAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReqResponse.this, Appointment.class);
                intent.putExtra("userK",userK);
                intent.putExtra("bankK",bankK);
                startActivity(intent);
            }
        });

        bDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReq();

            }
        }


        );
    }


    public void deleteReq() {

        mRef.child("Requests").child(userK).child(bankK).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                mRef.child("Requests").child(bankK).child(userK).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intentk = new Intent(ReqResponse.this, Apology.class);
                        startActivity(intentk);



                    }
                });


            }
        });
    }


    }





