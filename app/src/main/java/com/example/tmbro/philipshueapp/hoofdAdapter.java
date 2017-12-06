package com.example.tmbro.philipshueapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tmbro on 24-11-2017.
 */

    public class hoofdAdapter extends ArrayAdapter<HUELamp> {

        public hoofdAdapter(Context context, ArrayList<HUELamp> aas){
            super(context, 0,aas);}

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HUELamp aa = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.hoofd_adapter,parent,false);
            }

            TextView naam = (TextView) convertView.findViewById(R.id.naam_adapter);
            naam.setText(aa.getName());
            if(!aa.isOn()){
                convertView.setBackgroundColor(Color.BLACK);
                naam.setTextColor(Color.WHITE);
            } else if(aa.getBri() < 128){
                naam.setTextColor(Color.WHITE);
                convertView.setBackgroundColor(Color.HSVToColor(aa.getHsv()));
            } else {
                convertView.setBackgroundColor(Color.HSVToColor(aa.getHsv()));
            }
            return convertView;
        }

    }


