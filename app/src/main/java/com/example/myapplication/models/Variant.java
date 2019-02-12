package com.example.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {

    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("color")
    @Expose
    private String color;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}