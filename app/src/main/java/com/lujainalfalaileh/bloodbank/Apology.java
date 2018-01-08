package com.lujainalfalaileh.bloodbank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Apology extends AppCompatActivity {
    DatabaseReference mRef;
    Spinner sApology;
    Button bSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apology);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Apology");
        Bundle extras = getIntent().getExtras();

        final String bankK = extras.getString("bankK");
        final String userK = extras.getString("userK");


        sApology = findViewById(R.id.sApology);
        bSend = findViewById(R.id.bSend);
        mRef = FirebaseDatabase.getInstance().getReference().child("Apologies");

        final String apology = sApology.getSelectedItem().toString().trim();
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final apologyy apologyy = new apologyy(apology);
                mRef.child(userK).child(bankK).setValue(apologyy).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mRef.child(bankK).child(userK).setValue(apologyy).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @SuppressLint("ShowToast")
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Apology.this, "Apology sent successfully!", Toast.LENGTH_LONG);

                            }
                        });
                    }
                });

            }
        });
    }}
