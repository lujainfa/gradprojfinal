package com.lujainalfalaileh.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class appointmentAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Date> appointments;
    public appointmentAdapter(Context con,ArrayList<Date> appointments)
    {
        context=con;
        layoutInflater = LayoutInflater.from(context);
        this.appointments=appointments;
    }
    @Override
    public int getCount() {
        return appointments.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.displayavailable, null, false);
            holder = new ViewHolder();
            holder.month =  convertView.findViewById(R.id.bName);
            holder.day =  convertView.findViewById(R.id.O11);
            holder.bName =  convertView.findViewById(R.id.tGender);
            holder.year=convertView.findViewById(R.id.tBlood);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Date appointment=appointments.get(position);
        holder.day.setText(appointment.getDay());
        holder.month.setText(appointment.getMonth());
        holder.year.setText(appointment.getYear());
        holder.bName.setText(appointment.getBank());

        return convertView;
    }
    public class ViewHolder {
        TextView bName,month,day,year;
    }
    @Override
    public Object getItem(int position) {
        return appointments.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}

