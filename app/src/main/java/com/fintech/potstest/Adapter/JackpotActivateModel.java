package com.fintech.potstest.Adapter;

public class JackpotActivateModel {
    private int main_image;
    private int sub_image;

    public JackpotActivateModel(int main_image, int sub_image) {
        this.main_image = main_image;
        this.sub_image = sub_image;
    }

    public int getMain_image() {
        return main_image;
    }

    public void setMain_image(int main_image) {
        this.main_image = main_image;
    }

    public int getSub_image() {
        return sub_image;
    }

    public void setSub_image(int sub_image) {
        this.sub_image = sub_image;
    }
}
