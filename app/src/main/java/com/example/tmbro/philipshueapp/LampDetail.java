package com.example.tmbro.philipshueapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LampDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_detail);

        final String url = "http://145.48.205.33/api/iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB/lights";

        Intent intent = getIntent();
        HUELamp item = (HUELamp) intent.getSerializableExtra("LAMP_ITEM");

        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(item.getName());

        final SeekBar bri = (SeekBar) findViewById(R.id.briBar);
        final Switch onSwitch = (Switch) findViewById(R.id.onSwitch);

        onSwitch.setChecked(item.isOn());

        View decor = findViewById(R.id.decor);
        decor.setBackgroundColor(Color.HSVToColor(item.getHsv()));

        Button button = findViewById(R.id.applyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("on", onSwitch.isChecked());
                    jsonObject.put("bri", briBar.getProgress());
                    jsonObject.put("hue", hueBar.getProgress());
                    jsonObject.put("sat", satBar.getProgress());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {



                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LampDetail.this, "Request failed", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();


                            }
                        });

                // Access the RequestQueue through your singleton class.

                MySingleton.getInstance(LampDetail.this).addToRequestQueue(jsObjRequest);
            }
        });
    }
}
