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
import android.widget.AdapterView;
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

public class RecievedRequests extends AppCompatActivity {

    ListView Requests;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    ListingAdapter adapter;
    Map<String, String> map;


    String bankK;
    String userK;

    String Current = FirebaseAuth.getInstance().getCurrentUser().getUid();
    ArrayList<Users> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_requests);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donation Requests");
        Requests = findViewById(R.id.Requests);
        adapter = new ListingAdapter(RecievedRequests.this, users);
        Requests.setAdapter(adapter);
        getDataFromServer();
        databaseReference.keepSynced(true);
        Requests.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String name = users.get(position).getName();
                String age = users.get(position).getAge();
                String gender = users.get(position).getGender();


                bankK = FirebaseAuth.getInstance().getCurrentUser().getUid();
                userK = map.get(users.get(position).getName());

                Intent intent = new Intent(RecievedRequests.this, UserReqShow.class);

                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("age", age);

                intent.putExtra("userK", userK);
                intent.putExtra("bankK", Current);


                startActivity(intent);
            }
        });


    }



    public void getDataFromServer() {
        map = new HashMap<>();

        databaseReference.child("Requests").child(Current).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    for ( final DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        if (postSnapShot.exists()) {
                       // Toast.makeText(RecievedRequests.this,postSnapShot.getKey(),Toast.LENGTH_LONG).show();

                        databaseReference.child("Users").child(postSnapShot.getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Users user1 = dataSnapshot.getValue(Users.class);
                                String name = user1.getName();
                                map.put(name, postSnapShot.getKey());
                                users.add(user1);

                                adapter.notifyDataSetChanged();


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }


                        });

                    }
                    else{
                            Toast.makeText(RecievedRequests.this,"No requests received",Toast.LENGTH_LONG).show();
                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            private void showProgressDialog() {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(RecievedRequests.this);
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
        ArrayList<Users> users;
        public ListingAdapter(Context con, ArrayList<Users> users)
        {
            context=con;
            layoutInflater = LayoutInflater.from(context);
            this.users=users;
        }
        @Override
        public int getCount() {
            return users.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.activity_display_requests, null, false);
                holder = new ViewHolder();
                holder.name =  convertView.findViewById(R.id.Name);
                holder.age = convertView.findViewById(R.id.Age);
                holder.gender = convertView.findViewById(R.id.tGender);
                holder.blood=convertView.findViewById(R.id.tBlood);
                convertView.setTag(holder);




            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Users user1=users.get(position);
            holder.name.setText(user1.getName());
            holder.age.setText(user1.getAge());
            holder.gender.setText(user1.getGender());
            holder.blood.setText(user1.getBlood());


            return convertView;
        }
        public class ViewHolder  {
            TextView name, age,gender,blood;

        }
        @Override
        public Object getItem(int position) {
            return users.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

    }


}