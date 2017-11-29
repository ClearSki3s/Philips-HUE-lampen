package com.example.tmbro.philipshueapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class LampDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_detail);



        Intent intent = getIntent();
        HUELamp item = (HUELamp) intent.getSerializableExtra("LAMP_ITEM");

        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(item.getName());

        SeekBar briBar = (SeekBar) findViewById(R.id.briBar);
        briBar.setMax(254);
        briBar.setProgress(item.getBri());

        SeekBar satBar = (SeekBar) findViewById(R.id.satBar);
        satBar.setMax(254);
        satBar.setProgress(item.getSat());

        SeekBar hueBar = (SeekBar) findViewById(R.id.hueBar);
        hueBar.setMax(182);
        hueBar.setProgress(item.getHue());

        Switch onSwitch = (Switch) findViewById(R.id.onSwitch);

        onSwitch.setChecked(item.isOn());




        View decor = findViewById(R.id.decor);
        decor.setBackgroundColor(Color.HSVToColor(item.getHsv()));
    }

}
