package com.lujainalfalaileh.bloodbank;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BloodData extends AppCompatActivity {

    private DatabaseReference my;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_data);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Bank Information Modification");


        my=FirebaseDatabase.getInstance().getReference("BloodBanks");
        my.keepSynced(true);
        Button bSave;
        bSave=findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBank(((EditText)findViewById(R.id.all_banks_name)).getText().toString(),((EditText)findViewById(R.id.all_banks_phone)).getText().toString(),
                        ((Spinner)findViewById(R.id.all_banks_status)).getSelectedItem().toString());
            }



   public void AddBank(String name, String phone, String status){
        @Nullable

        FirebaseUser iD = FirebaseAuth.getInstance().getCurrentUser();
        Banks Bank = new Banks(name,phone,status);


       my.child(iD.getUid()).setValue(Bank);

    }
});
    };}

