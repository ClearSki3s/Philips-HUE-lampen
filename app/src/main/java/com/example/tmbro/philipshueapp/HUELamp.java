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



    public HUELamp() {
    }

    public HUELamp(JSONObject json) {

        try {


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
