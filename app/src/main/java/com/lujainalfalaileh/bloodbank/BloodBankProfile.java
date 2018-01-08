package com.lujainalfalaileh.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;

public class BloodBankProfile extends AppCompatActivity  implements View.OnClickListener{
    FirebaseAuth Auth;
    FirebaseUser Blood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_profile);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Bank's Home Page");
        TextView mStatus;
        mStatus=findViewById(R.id.mStatus);
        Blood=Auth.getInstance().getCurrentUser();
        String a=Blood.getEmail().toString();
        mStatus.setText(a);

        Button bData,bSignOut,bRequests,bAvailable;
        bRequests=findViewById(R.id.bRequests);
        bRequests.setOnClickListener(this);
        bData =findViewById(R.id.bData);
        bData.setOnClickListener(this);
        bSignOut=findViewById(R.id.bSignOut);
        bSignOut.setOnClickListener(this);
        bAvailable=findViewById(R.id.Available);
        bAvailable.setOnClickListener(this);

        TextView dateAndtime;


        Calendar calender;

        dateAndtime=findViewById(R.id.dateAndtime);
        calender= Calendar.getInstance();
        String dT= DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());
        dateAndtime.setText(dT);


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i==R.id.bData){
            Intent newPage=new Intent(BloodBankProfile.this,BloodData.class);
            startActivity(newPage);
        }
        if(i==R.id.bSignOut) {
            Auth.getInstance().signOut();
            finish();

        }
            if(i==R.id.bRequests){
                Intent newPage=new Intent(BloodBankProfile.this,RecievedRequests.class);
                startActivity(newPage);
        }
        if(i==R.id.Available) {
            Intent newPage = new Intent(BloodBankProfile.this, AvailableBtypes.class);
            startActivity(newPage);
        }
    }
}


