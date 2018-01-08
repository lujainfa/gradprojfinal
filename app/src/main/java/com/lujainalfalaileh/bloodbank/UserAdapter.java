package com.lujainalfalaileh.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


    public class UserAdapter extends BaseAdapter {
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Users> users;
        public UserAdapter(Context con,ArrayList<Users> users)
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
                holder.age =  convertView.findViewById(R.id.Age);
                holder.gender =  convertView.findViewById(R.id.tGender);
                holder.blood=convertView.findViewById(R.id.tBlood);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Users user=users.get(position);
            holder.name.setText(user.getName());
            holder.age.setText(user.getAge());
            holder.gender.setText(user.getGender());
            holder.blood.setText(user.getBlood());

            return convertView;
        }
        public class ViewHolder {
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

