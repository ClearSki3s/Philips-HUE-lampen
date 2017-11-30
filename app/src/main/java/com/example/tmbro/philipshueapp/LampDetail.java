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

public class LampDetail extends AppCompatActivity implements View.OnClickListener {
    SeekBar briBar;
    SeekBar satBar;
    SeekBar hueBar;
    Switch onSwitch;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_detail);

        Intent intent = getIntent();
        HUELamp item = (HUELamp) intent.getSerializableExtra("LAMP_ITEM");
        url = "http://145.48.205.33/api/iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB/lights/" + item.getId() + "/state";
        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(item.getName());

        briBar = (SeekBar) findViewById(R.id.briBar);
        briBar.setMax(254);
        briBar.setProgress(item.getBri());

        satBar = (SeekBar) findViewById(R.id.satBar);
        satBar.setMax(254);
        satBar.setProgress(item.getSat());

        hueBar = (SeekBar) findViewById(R.id.hueBar);
        hueBar.setMax(65535);
        hueBar.setProgress(item.getHue());

        onSwitch = (Switch) findViewById(R.id.onSwitch);

        onSwitch.setChecked(item.isOn());
        

        View decor = findViewById(R.id.decor);
        decor.setBackgroundColor(Color.HSVToColor(item.getHsv()));

        Button button = findViewById(R.id.applyButton);
        button.setOnClickListener(this);

        briBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                View decor = findViewById(R.id.decor);
                decor.setBackgroundColor(Color.HSVToColor(getArray()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                View decor = findViewById(R.id.decor);
                decor.setBackgroundColor(Color.HSVToColor(getArray()));
            }
        });
        satBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                View decor = findViewById(R.id.decor);
                decor.setBackgroundColor(Color.HSVToColor(getArray()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                View decor = findViewById(R.id.decor);
                decor.setBackgroundColor(Color.HSVToColor(getArray()));
            }
        });
        hueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                View decor = findViewById(R.id.decor);
                decor.setBackgroundColor(Color.HSVToColor(getArray()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                View decor = findViewById(R.id.decor);
                decor.setBackgroundColor(Color.HSVToColor(getArray()));
            }
        });
    }

    private float[] getArray() {
        float[]hsv = new float[3];
        hsv[0]= hueBar.getProgress()/(float)182.0;
        hsv[1]= satBar.getProgress()/(float)254.0;
        hsv[2]= briBar.getProgress()/(float)254.0;
        return hsv;
    }

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
                        Log.d("response", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();


                    }
                });

        // Access the RequestQueue through your singleton class.

        MySingleton.getInstance(LampDetail.this).addToRequestQueue(jsObjRequest);

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
