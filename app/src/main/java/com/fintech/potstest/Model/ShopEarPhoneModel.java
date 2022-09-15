package com.fintech.potstest.Model;

public class ShopEarPhoneModel {
    private int Image;
    private String Discount;
    private String Discount_txt;
    private String NewDiscount;
    private String NewDiscount_txt;
    private String Name;
    private String price;

    public ShopEarPhoneModel(int image, String discount, String discount_txt, String newDiscount, String newDiscount_txt, String name, String price) {
        Image = image;
        Discount = discount;
        Discount_txt = discount_txt;
        NewDiscount = newDiscount;
        NewDiscount_txt = newDiscount_txt;
        Name = name;
        this.price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getDiscount_txt() {
        return Discount_txt;
    }

    public void setDiscount_txt(String discount_txt) {
        Discount_txt = discount_txt;
    }

    public String getNewDiscount() {
        return NewDiscount;
    }

    public void setNewDiscount(String newDiscount) {
        NewDiscount = newDiscount;
    }

    public String getNewDiscount_txt() {
        return NewDiscount_txt;
    }

    public void setNewDiscount_txt(String newDiscount_txt) {
        NewDiscount_txt = newDiscount_txt;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
