package com.example.searchfiltervolley;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseModal {

    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("phone")
    private String phone;

    public CourseModal(String name, String image, String phone) {
        this.name = name;
        this.image = image;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
