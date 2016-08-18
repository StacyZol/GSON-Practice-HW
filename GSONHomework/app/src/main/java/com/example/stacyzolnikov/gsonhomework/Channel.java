package com.example.stacyzolnikov.gsonhomework;

/**
 * Created by stacyzolnikov on 8/16/16.
 */
public class Channel {
    private Item item;
    private String title;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String toString() {
        return "("+ item +")";

    }
}
