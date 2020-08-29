package com.example.watery.utils;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class RecordBean {
    private int waterTypeImage;
    private String recordIntake;
    private String typeName;
    private String date;

    public RecordBean(int waterTypeImage, String recordIntake, String typeName, String date) {
        this.waterTypeImage = waterTypeImage;
        this.recordIntake = recordIntake;
        this.typeName = typeName;
        this.date = date;
    }

    public int getWaterTypeImage() {
        return waterTypeImage;
    }

    public void setWaterTypeImage(int waterTypeImage) {
        this.waterTypeImage = waterTypeImage;
    }

    public String getRecordIntake() {
        return recordIntake;
    }

    public void setRecordIntake(String recordIntake) {
        this.recordIntake = recordIntake;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
