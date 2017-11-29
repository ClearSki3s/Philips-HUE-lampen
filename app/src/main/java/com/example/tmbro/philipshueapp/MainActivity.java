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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView hoofdList;
    ArrayAdapter adapter;
    String token;

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

        //fetch();
        //String json = JsonUtil.loadJSONFromAsset(MainActivity.this);
//        Log.d(TAG, "onCreate: json from asset " + json );

        //art = blindWallsBreda.createFromJson(json);
        String url = "http://145.48.205.33/api/iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB/lights";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        for(int i = 1; i < response.length() + 2; i++){
                            try {
                                lampen.add(new HUELamp(response.getJSONObject(String.valueOf(i))));

                                Log.d("REE", response.getJSONObject(String.valueOf(i)).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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

        // Access the RequestQueue through your singleton class.

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }
    @Override
    public void onItemClick(AdapterView<?> adapterview, View view, int i, long l){
        HUELamp item = lampen.get(i);
        Intent intent = new Intent(getApplicationContext(),LampDetail.class);
        intent.putExtra("LAMP_ITEM", item);
        startActivity(intent);
    }
}

