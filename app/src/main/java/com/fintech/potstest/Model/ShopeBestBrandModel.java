package com.fintech.potstest.Model;

public class ShopeBestBrandModel {
    private String Name;
    private int Image;

    public ShopeBestBrandModel(String name, int image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
