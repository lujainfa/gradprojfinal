package com.lujainalfalaileh.bloodbank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AvailableBtypes extends AppCompatActivity {
    DatabaseReference mRef;
    DatabaseReference yRef;
    FirebaseUser iD = FirebaseAuth.getInstance().getCurrentUser();

   String cO11,cO22,cB11,cB22,cA11,cA22,cAB11,cAB22;
    CheckBox cO1,cO2,cB1,cB2,cA1,cA2,cAB1,cAB2;
    CheckBox cWholeBlood,cPlatelet,cRedCells;
    String cWholeBlood1,cPlatelet1,cRedCells1,bName;
    Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_btypes);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Needed Donations");




        final String CuId=iD.getUid();
        final SharedPreferences checkPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = checkPref.edit();
        mRef= FirebaseDatabase.getInstance().getReference().child("Availability");
        yRef=FirebaseDatabase.getInstance().getReference().child("BloodBanks");
        yRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Banks bank = dataSnapshot.getValue(Banks.class);
                bName=bank.getName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        cO1=findViewById(R.id.cO1);
        cO1.setChecked(false);
        cO2=findViewById(R.id.cO2);
        cO2.setChecked(false);
        cB1=findViewById(R.id.cB1);
        cB1.setChecked(false);
        cB2=findViewById(R.id.cB2);
        cB2.setChecked(false);
        cA1=findViewById(R.id.cA1);
        cA1.setChecked(false);
        cA2=findViewById(R.id.cA2);
        cA2.setChecked(false);
        cAB1=findViewById(R.id.cAB1);
        cAB1.setChecked(false);
        cAB2=findViewById(R.id.cAB2);
        cAB2.setChecked(false);
        cWholeBlood=findViewById(R.id.cWholeBlood);
        cWholeBlood.setChecked(false);
        cPlatelet=findViewById(R.id.cPlatelet);
        cPlatelet.setChecked(false);
        cRedCells=findViewById(R.id.cRedCells);
        cRedCells.setChecked(false);
        cRedCells.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cRedCells.isChecked()){
                    cRedCells1="true";
                    editor.putBoolean("redcells",true);

                }
                if(!cRedCells.isChecked()){

                    cRedCells1="false";
                    editor.putBoolean("redcells",false);

                }
                editor.commit();
            }
        });
        cPlatelet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cPlatelet.isChecked()){
                    cPlatelet1="true";
                    editor.putBoolean("p",true);

                }
               else{

                    cPlatelet1="false";
                    editor.putBoolean("p",false);

                }
                editor.commit();
            }
        });
        cWholeBlood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cWholeBlood.isChecked()){
                    cWholeBlood1="true";
                    editor.putBoolean("w",true);

                }
                if(!cWholeBlood.isChecked()){

                    cWholeBlood1="false";
                    editor.putBoolean("w",false);

                }
                editor.commit();
            }
        });
        cO2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cO2.isChecked()){
                    cO22="true";
                    editor.putBoolean("o2",true);


                }
                if(!cO2.isChecked()){

                    cO22="false";
                    editor.putBoolean("o2",false);

                }
                editor.commit();
            }
        });
        cO1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cO1.isChecked()){
                    cO11="true";
                    editor.putBoolean("o1",true);

                }
                if(!cO1.isChecked()){

                    cO11="false";
                    editor.putBoolean("o1",false);

                }
                editor.commit();
            }
        });
        cB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cB2.isChecked()){
                    cB22="true";
                    editor.putBoolean("b2",true);

                }
                if(!cB2.isChecked()){

                    cB22="false";
                    editor.putBoolean("b2",false);

                }
                editor.commit();
            }
        });
        cB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cB1.isChecked()){
                    cB11="true";
                    editor.putBoolean("b1",true);

                }
                if(!cB1.isChecked()){

                    cB11="false";
                    editor.putBoolean("b1",false);

                }
                editor.commit();
            }
        });
        cA2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cA2.isChecked()){
                    cA22="true";
                    editor.putBoolean("a2",true);

                }
                if(!cA2.isChecked()){

                    cA22="false";
                    editor.putBoolean("a2",false);

                }
                editor.commit();
            }
        });
        cA1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cA1.isChecked()){
                    cA11="true";
                    editor.putBoolean("a1",true);

                }
                if(!cA1.isChecked()){

                    cA11="false";
                    editor.putBoolean("a1",false);

                }
                editor.commit();
            }
        });
        cAB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cAB1.isChecked()){
                    cAB11="true";
                    editor.putBoolean("ab1",true);
                }
                if(!cAB1.isChecked()){

                    cAB11="false";
                    editor.putBoolean("ab1",false);

                }
                editor.commit();

            }
        });
        cAB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(cAB2.isChecked()){
                    cAB22="true";
                    editor.putBoolean("ab2",true);

                }
                if(!cAB2.isChecked()){

                    cAB22="false";
                    editor.putBoolean("ab2",false);


                }
                editor.commit();
            }

        });
        cO1.setChecked(checkPref.getBoolean("o1",false));
        cO2.setChecked(checkPref.getBoolean("o2",false));
        cA1.setChecked(checkPref.getBoolean("a1",false));
        cA2.setChecked(checkPref.getBoolean("a2",false));
        cB1.setChecked(checkPref.getBoolean("b1",false));
        cB2.setChecked(checkPref.getBoolean("b2",false));
        cAB1.setChecked(checkPref.getBoolean("ab1",false));
        cAB2.setChecked(checkPref.getBoolean("ab2",false));
        cWholeBlood.setChecked(checkPref.getBoolean("w",false));
        cPlatelet.setChecked(checkPref.getBoolean("p",false));
        cRedCells.setChecked(checkPref.getBoolean("redcells",false));


        bSave=findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Availability Data = new Availability(cO11,cO22,cB11,cB22,
                       cA11,cA22,cAB11,cAB22,cWholeBlood1,cPlatelet1,cRedCells1,bName);
                mRef.child(CuId).setValue(Data);
                Toast.makeText(AvailableBtypes.this,"Changes Saved Successfully!",Toast.LENGTH_LONG).show();
            }
        });





    }


}
