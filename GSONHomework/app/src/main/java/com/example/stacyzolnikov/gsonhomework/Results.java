package com.example.stacyzolnikov.gsonhomework;

/**
 * Created by stacyzolnikov on 8/16/16.
 */
public class Results {
private Channel channel;
    private Item item;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }



    public String toString(){
        return " ( " + channel+" ) " + " ( " + item + " )";
    }
}
