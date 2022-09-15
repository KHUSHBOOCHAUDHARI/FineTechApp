package com.fintech.potstest.Model;

public class ShopPhoneModel {
    private int Image;
    private String Discount;
    private String Discount_txt;
    private String ExtraDiscount;
    private String ExtraDiscount_txt;
    private String Name;
    private String price;

    public ShopPhoneModel(int image, String discount, String discount_txt, String extraDiscount, String extraDiscount_txt, String name, String price) {
        Image = image;
        Discount = discount;
        Discount_txt = discount_txt;
        ExtraDiscount = extraDiscount;
        ExtraDiscount_txt = extraDiscount_txt;
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

    public String getExtraDiscount() {
        return ExtraDiscount;
    }

    public void setExtraDiscount(String extraDiscount) {
        ExtraDiscount = extraDiscount;
    }

    public String getExtraDiscount_txt() {
        return ExtraDiscount_txt;
    }

    public void setExtraDiscount_txt(String extraDiscount_txt) {
        ExtraDiscount_txt = extraDiscount_txt;
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
