package com.example.stacyzolnikov.gsonhomework;

/**
 * Created by stacyzolnikov on 8/16/16.
 */
public class Query {
    Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
            public String toString() {
                return "("+results+")";
        }
}
