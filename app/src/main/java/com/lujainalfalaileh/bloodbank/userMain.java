package com.lujainalfalaileh.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class userMain extends  BaseActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener{
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User's Home Page");


        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);
        TextView dateAndtime,mStatusTextView;
        mStatusTextView=findViewById(R.id.mStatusTextView);
        dateAndtime=findViewById(R.id.dateAndtime);
        Calendar calender;
        FirebaseUser user;
        user=FirebaseAuth.getInstance().getCurrentUser();
        mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
        calender= Calendar.getInstance();
        String dT= DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());
        dateAndtime.setText(dT);
        findViewById(R.id.Nearby_button).setOnClickListener(this);
        findViewById(R.id.Donate_button).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        finish();
        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        showProgressDialog();
                      Intent intent = new Intent(userMain.this,GoogleSignInActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.getCurrentUser().delete();

        final DatabaseReference x= FirebaseDatabase.getInstance().getInstance().getReference();
        x.child("Users").child(mAuth.getCurrentUser().getUid()).removeValue();
        x.child("Requests").child(mAuth.getCurrentUser().getUid()).removeValue();
        FirebaseDatabase.getInstance().getReference().child("Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    if(postSnapshot.child(mAuth.getCurrentUser().getUid()).exists()){
                        x.child("Requests").child(dataSnapshot.getKey()).child(mAuth.getCurrentUser().getUid()).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Google revoke access

        finish();
    }

    @Override
    public void onClick(View v) {
       int i=v.getId();
    if (i == R.id.sign_out_button) {
        signOut();
    } else if (i == R.id.disconnect_button) {
        revokeAccess();
        Intent med=new Intent(userMain.this,GoogleSignInActivity.class);
        startActivity(med);
    }else if (i==R.id.Donate_button){
        showProgressDialog();
        Intent med=new Intent(userMain.this,MedicalForm.class);
        startActivity(med);
    }else if (i==R.id.Appointments) {
        showProgressDialog();
        Intent med = new Intent(userMain.this, AppointmentShow.class);
        startActivity(med);


    }else if(i==R.id.Nearby_button) {
        showProgressDialog();
        Intent intent = new Intent(userMain.this, MapsActivity.class);
        startActivity(intent);
    }




}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
