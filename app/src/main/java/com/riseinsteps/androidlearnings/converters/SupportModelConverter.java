package com.riseinsteps.androidlearnings.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.riseinsteps.androidlearnings.model.Support;

import java.lang.reflect.Type;

public class SupportModelConverter {


    @TypeConverter
    public String fromSupport(Support support) {
        if (support == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Support>() {
        }.getType();
        String json = gson.toJson(support, type);
        return json;
    }

    @TypeConverter
    public Support toSupport(String data) {
        if (data == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Support>() {
        }.getType();
        Support support = gson.fromJson(data, type);
        return support;
    }
}
