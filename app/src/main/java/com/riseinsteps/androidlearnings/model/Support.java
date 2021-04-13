package com.riseinsteps.androidlearnings.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Support {

    @PrimaryKey(autoGenerate = true)
    private Integer supportId;

    private String url;
    private String text;

    public Support(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public Integer getSupportId() {
        return supportId;
    }

    public void setSupportId(Integer supportId) {
        this.supportId = supportId;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }
}
