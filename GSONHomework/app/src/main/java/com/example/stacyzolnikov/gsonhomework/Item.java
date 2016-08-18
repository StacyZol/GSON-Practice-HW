package com.example.stacyzolnikov.gsonhomework;

import java.util.ArrayList;

/**
 * Created by stacyzolnikov on 8/16/16.
 */
public class Item {

    private String title;
    private ArrayList<Forecast> forecast;

    public ArrayList<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<Forecast> forecast) {
        this.forecast = forecast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


   public String toString(){
       return " ( "+forecast+" ) ";
   }
}
