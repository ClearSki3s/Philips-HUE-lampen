package com.example.tmbro.philipshueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LampDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_detail);
    }

    Intent intent = getIntent();
    HUELamp item = (HUELamp) intent.getSerializableExtra("LAMP_ITEM");
}
