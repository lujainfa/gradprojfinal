package com.lujainalfalaileh.bloodbank;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MedicalForm extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.medical_form);

            Toolbar toolbar=findViewById(R.id.toolb);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Form");

            final EditText tName,tAge;
            final Spinner  gender_spinner,blood_spinner;

            tAge=findViewById(R.id.Age);
            tName=findViewById(R.id.Name);
            blood_spinner=findViewById(R.id.blood_spinner);
            gender_spinner=findViewById(R.id.gender_spinner);







            (findViewById(R.id.bNext)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     if (tName.getText().toString().length() == 0|| (tAge.getText().toString().length() == 0)){
                        Toast.makeText(MedicalForm.this, "You cannot leave any field blank", Toast.LENGTH_SHORT).show();

                    }


                    else

                    {
                        Intent next_page = new Intent(MedicalForm.this, MedicalForm2.class);
                        next_page.putExtra("gender", gender_spinner.getSelectedItem().toString());
                        next_page.putExtra("age", tAge.getText().toString());
                        next_page.putExtra("name", tName.getText().toString().trim());
                        next_page.putExtra("blood", blood_spinner.getSelectedItem().toString());
                        startActivity(next_page);
                        if(Integer.parseInt(tAge.getText().toString().trim()) <16){
                            Toast.makeText(MedicalForm.this,"Sorry, your age is under 16",Toast.LENGTH_SHORT).show();
                            Intent l=new Intent(getApplicationContext(),UnderAge.class);
                            startActivity(l);


                        }


                    }






                }

            });
        }


}













