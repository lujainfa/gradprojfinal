package com.lujainalfalaileh.bloodbank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BloodreqShow extends AppCompatActivity {
        private  DatabaseReference mRef=FirebaseDatabase.getInstance().getReference().child("Requests");
        String bankK,userK;
        String  mCurrent="not sent";
        DatabaseReference yRef=FirebaseDatabase.getInstance().getReference().child("Availability");
    String O1,O2,A1,A2,B1,B2,AB1,AB2,WholeBlood,RedCells,Platelets;
    TextView A11,A22,B11,B22,AB11,AB22,O11,O22,redCells,Platelet,whole;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodreq_show);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donation Request");
        final Button request;
        final TextView bName,bStatus;
        request=findViewById(R.id.request);
        bName=findViewById(R.id.bName);
        bStatus=findViewById(R.id.bStatus);
        Bundle extras= getIntent().getExtras();
        bName.setText(extras.getString("name"));
        bStatus.setText(extras.getString("stats"));
        A11=findViewById(R.id.A11);
        A22=findViewById(R.id.A22);
        B11=findViewById(R.id.B11);
        B22=findViewById(R.id.B22);
        AB11=findViewById(R.id.AB11);
        AB22=findViewById(R.id.AB22);
        O11=findViewById(R.id.O11);
        O22=findViewById(R.id.O22);
        redCells=findViewById(R.id.redcells);
        whole=findViewById(R.id.whole);
        Platelet=findViewById(R.id.platelets);



        bankK=extras.getString("bankK");
        userK=FirebaseAuth.getInstance().getCurrentUser().getUid();

        yRef.child(bankK).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                Availability available= dataSnapshot.getValue(Availability.class);

                O1= available.getcO11();
                if(O1!=null) {
                    check(O1,O11);
                }
                O2 = available.getcO22();
                if(O2!=null) {
                    check(O2,O22);

                }
                A1=available.getcA11();
                if(A1!=null) {
                    check(A1,A11);
                }
                A2=available.getcA22();
                if(A2!=null) {
                    check(A2,A22);
                }
                B1=available.getcB11();
                if(B1!=null){

                    check(B1,B11);

                }
                B2=available.getcB22();
                if(B2!=null) {
                    check(B2,B22);
                }
                AB1=available.getcAB11();
                if(AB1!=null) {
                    check(AB1,AB11);

                }
                AB2=available.getcAB22();
                if(AB2!=null) {
                    check(AB2,AB22);
                }
                WholeBlood=available.getcWholeBlood1();
                if(WholeBlood!=null) {
                    check(WholeBlood,whole);
                }
                RedCells=available.getcRedCells1();
                if(RedCells!=null) {
                    check(RedCells,redCells);

                }
                Platelets=available.getcPlatelet1();
                if(Platelets!=null) {
                    check(Platelets,Platelet);
                }





            }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        if(mCurrent.equals("not sent")){
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.setEnabled(false);

                    mRef.child(userK).child(bankK).child("mCurrent").setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override

                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mRef.child(bankK).child(userK).child("mCurrent").setValue("received").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        request.setEnabled(true);
                                        mCurrent="sent";
                                        request.setText("Cancel Request");
                                       Toast.makeText(BloodreqShow.this,"Request Sent Successfully!",Toast.LENGTH_LONG).show();

                                    }
                                });
                            }else
                            {
                                Toast.makeText(BloodreqShow.this,"Failed sending request",Toast.LENGTH_LONG).show();
                            }
                        }

                        });

                if(mCurrent.equals("sent")){
                    mRef.child(userK).child(bankK).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            mRef.child(bankK).child(userK).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    request.setEnabled(true);
                                    mCurrent="not sent";
                                    request.setText("Send Request");
                                    Toast.makeText(BloodreqShow.this,"Request Removed  Successfully!",Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    });
                }

        }
    })
;}}
    private void check(String blood,TextView y){
        String value="true";
        if(blood.equals("true")) {
            y.setVisibility(View.VISIBLE);
        }else {
            y.setVisibility(View.INVISIBLE);


        }

    }
    }


