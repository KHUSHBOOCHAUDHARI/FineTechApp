package com.fintech.potstest.Adapter;

public class PodcastModel {
    private String title;
    private int logo;
    private int product;
    private int discount;
    private int background;
    private int colorcode;

    public PodcastModel(String title, int logo, int product, int discount, int background, int colorcode) {
        this.title = title;
        this.logo = logo;
        this.product = product;
        this.discount = discount;
        this.background = background;
        this.colorcode = colorcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getColorcode() {
        return colorcode;
    }

    public void setColorcode(int colorcode) {
        this.colorcode = colorcode;
    }
}
