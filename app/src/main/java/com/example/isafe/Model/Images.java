package com.example.isafe.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Images implements Serializable {
    ArrayList<String> imageUrls;

    public Images(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Images() {
    }
}
