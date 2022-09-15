package com.fintech.potstest.Model;

public class ShopeWatchModel {
    private int Image_Background;
    private int Image;
    private String Name;
    private String Price;

    public ShopeWatchModel(int image_Background, int image, String name, String price) {
        Image_Background = image_Background;
        Image = image;
        Name = name;
        Price = price;
    }

    public int getImage_Background() {
        return Image_Background;
    }

    public void setImage_Background(int image_Background) {
        Image_Background = image_Background;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
