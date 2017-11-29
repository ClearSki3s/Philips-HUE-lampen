package com.example.tmbro.philipshueapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmbro on 24-11-2017.
 */

public class HUELamp implements Serializable {

    private static transient JSONObject state;
    private String id;
    private boolean on;
    private int bri;
    private int hue;
    private int sat;
    private String effect;
    private String type;
    private String name;


    private float[] hsv;

    public HUELamp(JSONObject json, String key) {

        try {
            id = key;
            state = json.getJSONObject("state");
            on = state.getBoolean("on");
            bri = state.getInt("bri");
            hue = state.getInt("hue");
            sat = state.getInt("sat");
            effect = state.getString("effect");

            type = json.getString("type");
            name = json.getString("name");

            hsv = new float[3];
            hsv[0]= hue/(float)182.0;
            hsv[1]= sat/(float)254.0;
            hsv[2]= bri/(float)254.0;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getState() {
        return state;
    }

    public boolean isOn() {
        return on;
    }

    public int getBri() {
        return bri;
    }

    public int getHue() {
        return hue;
    }

    public int getSat() {
        return sat;
    }

    public String getEffect() {
        return effect;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public float[] getHsv() {
        return hsv;
    }

    public String getId() { return id; }
}

