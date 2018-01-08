package com.lujainalfalaileh.bloodbank;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class ShowBloodBanks extends AppCompatActivity {
    ListView allbanks;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    ListingAdapter adapter;
    Map<String, String> map;


    ArrayList<Banks> bank=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbloodbanks);
        Toolbar toolbar=findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registered Blood Banks");

        allbanks=findViewById(R.id.allbanks);
        adapter = new ListingAdapter(ShowBloodBanks.this, bank);
        allbanks.setAdapter(adapter);
        getDataFromServer();
        allbanks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                                 String name=bank.get(position).getName();
                                                String phone=bank.get(position).getPhone();
                                                String status=bank.get(position).getStatus();


                                                String userK=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                String bankK=map.get(bank.get(position).getName());
                                               Intent intent=new Intent(ShowBloodBanks.this,BloodreqShow.class);

                                                intent.putExtra("name",name);
                                                intent.putExtra("stats",status);
                                                intent.putExtra("phone",phone);
                                                intent.putExtra("userK",userK);
                                                intent.putExtra("bankK",bankK);

                                                startActivity(intent);
                                            }
                                        });





    }
    // getting the data from UserNode at Firebase and then adding the users in Arraylist and setting it to Listview
    public void getDataFromServer()
    { map = new LinkedHashMap<String, String>();
        showProgressDialog();
        databaseReference.child("BloodBanks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                         Banks bank1=postSnapShot.getValue(Banks.class);
                         String key = postSnapShot.getKey();
                         map.put(bank1.getName(),key);
                         bank.add(bank1);
                         adapter.notifyDataSetChanged();
                    }


                }
                hideProgressDialog();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }

        });
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(ShowBloodBanks.this);
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
    private class ListingAdapter extends BaseAdapter {
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Banks> bank;
        public ListingAdapter(Context con, ArrayList<Banks> bank)
        {
            context=con;
            layoutInflater = LayoutInflater.from(context);
            this.bank=bank;
        }
        @Override
        public int getCount() {
            return bank.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.activity_display_bloodbank, null, false);
                holder = new ViewHolder();
                holder.name =  convertView.findViewById(R.id.all_banks_name);
                holder.phone = convertView.findViewById(R.id.all_banks_phone);
                holder.status = convertView.findViewById(R.id.all_banks_status);
                convertView.setTag(holder);




            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Banks bank1=bank.get(position);
            holder.name.setText(bank1.getName());
            holder.phone.setText(bank1.getPhone());
            holder.status.setText(bank1.getStatus());


            return convertView;
        }
        public class ViewHolder  {
            TextView name, phone,status;

        }
        @Override
        public Object getItem(int position) {
            return bank.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

    }

}