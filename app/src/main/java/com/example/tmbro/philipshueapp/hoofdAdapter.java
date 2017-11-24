package com.example.tmbro.philipshueapp;

import android.content.Context;
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


            return convertView;
        }
    }


