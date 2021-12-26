package com.aminov.a3ticketekz;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Passenger> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Passenger> passengers;

    public MyAdapter(Context context, int resource, ArrayList<Passenger> passengers) {
        super(context, resource, passengers);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.passengers = passengers;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);
        Passenger passenger = passengers.get(position);
        ((TextView) convertView.findViewById(R.id.tvFio)).setText(passenger.getFio());
        ((TextView) convertView.findViewById(R.id.tvDateBirth)).setText(passenger.getDateBirth());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView) convertView.findViewById(R.id.tvNameFlight)).setText(String.join(",", passenger.getNameFlight()));
        }
        return convertView;
    }
}
