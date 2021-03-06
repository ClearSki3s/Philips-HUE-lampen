package com.example.tmbro.philipshueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView hoofdList;
    ArrayAdapter adapter;
    String token;
    JsonObjectRequest jsObjRequest;

    public ArrayList<HUELamp> lampen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lampen = new ArrayList<HUELamp>();


        this.hoofdList = (ListView) findViewById(R.id.listview_main);


        adapter = new hoofdAdapter(this.getApplicationContext(), lampen);
        hoofdList.setAdapter(adapter);
        hoofdList.setOnItemClickListener(this);

        String url = "http://145.48.205.33/api/iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB/lights";

        jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                            try {
                                Iterator<String> iterator = response.keys();
                                while(iterator.hasNext()) {
                                    String key = iterator.next();
                                    lampen.add(new HUELamp(response.getJSONObject(key), key));
                                    Log.d("KEY", response.getJSONObject(key).toString());
                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();


                    }
                });




        Timer t = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        lampen.clear();
                        adapter.notifyDataSetChanged();
                        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsObjRequest);
                    }
                });
            }
        };

        t.scheduleAtFixedRate(task, 0, 5000);

    }
    @Override
    public void onItemClick(AdapterView<?> adapterview, View view, int i, long l){
        HUELamp item = lampen.get(i);
        Intent intent = new Intent(getApplicationContext(),LampDetail.class);
        intent.putExtra("LAMP_ITEM", item);
        startActivity(intent);
    }

}

