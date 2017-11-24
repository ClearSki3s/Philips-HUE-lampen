package com.example.tmbro.philipshueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
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

        SeekBar bri = (SeekBar) findViewById(R.id.briBar);
        //bri.set
    }
}
