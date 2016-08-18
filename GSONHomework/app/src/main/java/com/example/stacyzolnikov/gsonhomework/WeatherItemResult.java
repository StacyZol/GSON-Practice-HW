package com.example.stacyzolnikov.gsonhomework;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by stacyzolnikov on 8/16/16.
 */
public class WeatherItemResult {
    private static final String TAG = "WeatherItemResult";
    Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String toString() {
        // return forecast +" weather";}
        return String.valueOf(query);

    }
}

