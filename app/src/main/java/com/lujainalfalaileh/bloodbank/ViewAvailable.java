package com.lujainalfalaileh.bloodbank;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class ViewAvailable extends AppCompatActivity {
    ListView Available;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Availability");
    ListingAdapter adapter;
    Map<String, String> map;


    ArrayList<Availability> available=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available);
        Available=findViewById(R.id.Available);
        adapter = new ListingAdapter(ViewAvailable.this, available);
        Available.setAdapter(adapter);
        getDataFromServer();
        Available.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String bName=available.get(position).getbName();
                String cA11=available.get(position).getcA11();
                String cA22=available.get(position).getcA22();
                String cO11=available.get(position).getcO11();
                String cO22=available.get(position).getcO22();
                String cB11=available.get(position).getcB11();
                String cB22=available.get(position).getcB22();
                String cAB11=available.get(position).getcAB11();
                String cAB22=available.get(position).getcAB22();






            }

        });





    }
    // getting the data from UserNode at Firebase and then adding the users in Arraylist and setting it to Listview
    public void getDataFromServer()
    { map = new LinkedHashMap<String, String>();
        showProgressDialog();
        databaseReference.child("Availability").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        Availability availables1=postSnapShot.getValue(Availability.class);
                        String key = postSnapShot.getKey();
                        map.put(availables1.getbName(),key);
                        available.add( availables1 );
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
            mProgressDialog = new ProgressDialog(ViewAvailable.this);
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
        ArrayList<Availability> available;
        public ListingAdapter(Context con, ArrayList<Availability> available)
        {
            context=con;
            layoutInflater = LayoutInflater.from(context);
            this.available=available;
        }
        @Override
        public int getCount() {
            return available.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.displayavailable, null, false);
                holder = new ViewHolder();
                holder.bName =  convertView.findViewById(R.id.bName);
                holder.A11=findViewById(R.id.A11);
                holder.A22=findViewById(R.id.A22);
                holder.B11=findViewById(R.id.B11);
               holder.B22=findViewById(R.id.B22);
               holder.AB11=findViewById(R.id.AB11);
               holder.AB22=findViewById(R.id.AB22);
                holder.O11=findViewById(R.id.O11);
                holder.O22=findViewById(R.id.O22);
                holder.redcells=findViewById(R.id.redcells);
                holder.whole=findViewById(R.id.whole);
                holder.platelets=findViewById(R.id.platelets);
                convertView.setTag(holder);




            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Availability availables1=available.get(position);
            holder.bName.setText(availables1.getbName());
            holder.A11.setText(availables1.getcA11());
            holder.A22.setText(availables1.getcA22());
            holder.B11.setText(availables1.getcB11());
            holder.B22.setText(availables1.getcB22());
            holder.AB11.setText(availables1.getcAB11());
            holder.AB22.setText(availables1.getcAB22());
            holder.O11.setText(availables1.getcO11());
            holder.O22.setText(availables1.getcO22());
            holder.redcells.setText(availables1.getcRedCells1());
            holder.platelets.setText(availables1.getcPlatelet1());
            holder.whole.setText(availables1.getcWholeBlood1());
            Toast.makeText(ViewAvailable.this,availables1.getbName(),Toast.LENGTH_LONG).show();
            return convertView;
        }
        public class ViewHolder  {
            TextView bName,A11,A22,B11,B22,AB11,AB22,O22,O11,redcells,whole,platelets;

        }
        @Override
        public Object getItem(int position) {
            return available.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

    }

}