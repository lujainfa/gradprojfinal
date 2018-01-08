package com.lujainalfalaileh.bloodbank;

import  android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
        import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Appointment extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String bankK,userK;
    DatabaseReference mRef;
    DatabaseReference yRef;
    String bName;
    TextView dateAndtime;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Calendar calender;
    SimpleDateFormat simpleDateFormat;
    String dT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Appointment");

        final SharedPreferences checkPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = checkPref.edit();
        dateAndtime=findViewById(R.id.dateAndtime);
        mDisplayDate =  findViewById(R.id.tvDate);
        calender=Calendar.getInstance();
        dT= DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());

            dateAndtime.setText(dT);


        mDisplayDate.setText(checkPref.getString("date","no Appointment"));
        Bundle extras = getIntent().getExtras();
        bankK = extras.getString("bankK");
        userK = extras.getString("userK");
        mRef= FirebaseDatabase.getInstance().getReference().child("Appointments");
        yRef= FirebaseDatabase.getInstance().getReference().child("BloodBanks");
        yRef.child(bankK).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Banks bank=dataSnapshot.getValue(Banks.class);
                bName=bank.getName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button bChoose;
        bChoose=findViewById(R.id.bChoose);
        bChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Appointment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

               String Day= Integer.toString(day);
                String Month= Integer.toString(month);
                String Year =Integer.toString(year);
                Date date =new Date(Day,Month,Year,bName);
                mRef.child(userK).child(bankK).setValue(date);
                mRef.child(bankK).child(userK).setValue(date);


                month = month + 1;
                String date1= month + "-" + day + "-" + year;
                Toast.makeText(Appointment.this,"Appointment is sent successfully!",Toast.LENGTH_LONG).show();
                mDisplayDate.setText(date1);
                editor.putString("date",date1);
                editor.commit();
            }
        };
    }
}