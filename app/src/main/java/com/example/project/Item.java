package com.example.project;

import java.io.Serializable;

public class Item implements Serializable {
    private int imageResource;

    @Override
    public String toString() {
        return "Item{" +
                "imageResource=" + imageResource +
                ", text='" + text + '\'' +
                '}';
    }

    private  String text;

    public Item(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }
    public String getName() {
        return text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
}
