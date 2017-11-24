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

    private JSONObject state;
    private boolean on;
    private int bri;
    private int hue;
    private int sat;
    private String effect;
    private String type;
    private String name;

    public HUELamp(JSONObject json) {

        try {

            state = json.getJSONObject("state");
            on = state.getBoolean("on");
            bri = state.getInt("bri");
            hue = state.getInt("hue");
            sat = state.getInt("sat");
            effect = state.getString("effect");

            type = json.getString("type");
            name = json.getString("name");

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
}

