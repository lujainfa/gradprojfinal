package com.lujainalfalaileh.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class BloodBankSignIn extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private Button bSignIn;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Bank Sign In");
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null){
            finish();

            startActivity(new Intent(getApplicationContext(),BloodBankProfile.class));

            //go to profile activity
        }//track user if logged in or not
        progressDialog =new ProgressDialog(this);
        etEmail= findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        bSignIn=findViewById(R.id.bSignIn);

        bSignIn.setOnClickListener(this);

    }
    private void userLogin() {

        String Email = etEmail.getText().toString().trim();
        String Password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password)) {

            Toast.makeText(this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();//alert message
            return;
        }
        if (TextUtils.isEmpty(Email)) {
            //email is empty
            Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show();//alert message
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            //no password is entered
            Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing In Users...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.cancel();
                if(task.isSuccessful()){
                    //profile activity
                    finish();

                    startActivity(new Intent(BloodBankSignIn.this,BloodBankProfile.class));
                }else
                {
                    progressDialog.cancel();
                    Toast.makeText(BloodBankSignIn.this, "Couldn't sign in,"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();}
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v==bSignIn)
        {
            userLogin();
        }

    }


}