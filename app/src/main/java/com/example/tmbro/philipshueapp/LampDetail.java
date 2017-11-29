package com.example.tmbro.philipshueapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        SeekBar bri = (SeekBar) findViewById(R.id.briBar);
        Switch onSwitch = (Switch) findViewById(R.id.onSwitch);

        onSwitch.setChecked(item.isOn());

        View decor = findViewById(R.id.decor);
        decor.setBackgroundColor(Color.HSVToColor(item.getHsv()));
    }
}
