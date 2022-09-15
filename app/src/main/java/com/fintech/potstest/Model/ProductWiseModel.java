package com.fintech.potstest.Model;

public class ProductWiseModel {
    private int ImageView;
    private String Title;
    private String SubTitle;
    private String Price;


    public ProductWiseModel(int imageView, String title, String subTitle, String price) {
        ImageView = imageView;
        Title = title;
        SubTitle = subTitle;
        Price = price;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
