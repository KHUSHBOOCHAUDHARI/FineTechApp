package com.fintech.potstest.Model;

public class ShopTrendModel {
    private int Image;
    private String discounr;
    private String discounttitle;
    private String extra;

    public ShopTrendModel(int image, String discounr, String discounttitle, String extra) {
        Image = image;
        this.discounr = discounr;
        this.discounttitle = discounttitle;
        this.extra = extra;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getDiscounr() {
        return discounr;
    }

    public void setDiscounr(String discounr) {
        this.discounr = discounr;
    }

    public String getDiscounttitle() {
        return discounttitle;
    }

    public void setDiscounttitle(String discounttitle) {
        this.discounttitle = discounttitle;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
