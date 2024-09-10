package com.example.tdma;

import java.util.List;

public class Hotel {
    private String name;
    private String description;
    private String price;
    private List<Integer> imageResIds; // List of image resource IDs

    public Hotel(String name, String description, String price, List<Integer> imageResIds) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResIds = imageResIds;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public List<Integer> getImageResIds() {
        return imageResIds;
    }
}
