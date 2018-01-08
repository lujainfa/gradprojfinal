package com.lujainalfalaileh.bloodbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentShow extends AppCompatActivity {

    ListView appointments;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    ListingAdapter adapter;
    Map<String, String> map;


    String bankK;
    String userK;

    String Current = FirebaseAuth.getInstance().getCurrentUser().getUid();
    ArrayList<Date> dates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_show);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Appointment Details");
        appointments = findViewById(R.id.appointments);
        adapter = new ListingAdapter(AppointmentShow.this, dates);
        appointments.setAdapter(adapter);
        getDataFromServer();
        databaseReference.keepSynced(true);
      /**  appointments.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String name =dates.get(position).getBank();
                String day = dates.get(position).getDay();
                String month = dates.get(position).getMonth();
                String year = dates.get(position).getYear();


                String User = FirebaseAuth.getInstance().getCurrentUser().getUid();
                bankK = map.get(dates.get(position).getBank());

                Intent intent = new Intent(RecievedRequests.this, UserReqShow.class);

                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("age", age);

                intent.putExtra("userK", userK);
                intent.putExtra("bankK", Current);


                startActivity(intent);
            }
        });**/


    }



    public void getDataFromServer() {
        map = new HashMap<>();

        databaseReference.child("Appointments").child(Current).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for ( final DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                    if (postSnapShot.exists()) {
                        // Toast.makeText(RecievedRequests.this,postSnapShot.getKey(),Toast.LENGTH_LONG).show();

                        databaseReference.child("Appointments").child(postSnapShot.getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Date date1 = postSnapShot.getValue(Date.class);
                                String name = date1.getBank();
                                map.put(name, postSnapShot.getKey());
                                dates.add(date1);
                                Toast.makeText(AppointmentShow.this,date1.getBank(),Toast.LENGTH_LONG).show();

                                adapter.notifyDataSetChanged();


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }


                        });

                    }
                    else{
                        Toast.makeText(AppointmentShow.this,"No requests received",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            private void showProgressDialog() {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(AppointmentShow.this);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.setIndeterminate(true);
                }
                mProgressDialog.show();
            }

            private void hideProgressDialog() {
                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

            }
        });
    }



    private class ListingAdapter extends BaseAdapter {
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Date> dates;
        public ListingAdapter(Context con, ArrayList<Date> users)
        {
            context=con;
            layoutInflater = LayoutInflater.from(context);
            this.dates=users;
        }
        @Override
        public int getCount() {
            return dates.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.appshow, null, false);
                holder = new ViewHolder();
                holder.name =  convertView.findViewById(R.id.bName);
                holder.day = convertView.findViewById(R.id.day);
                holder.month = convertView.findViewById(R.id.month);
                holder.year=convertView.findViewById(R.id.year);
                convertView.setTag(holder);




            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Date date=dates.get(position);
            holder.name.setText(date.getBank());
            holder.day.setText(date.getDay());
            holder.month.setText(date.getMonth());
            holder.year.setText(date.getYear());


            return convertView;
        }
        public class ViewHolder  {
            TextView name, day,month,year;


        }
        @Override
        public Object getItem(int position) {
            return dates.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

    }


}