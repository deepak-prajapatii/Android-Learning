package com.riseinsteps.androidlearnings.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.riseinsteps.androidlearnings.model.Data;
import com.riseinsteps.androidlearnings.model.Model;
import com.riseinsteps.androidlearnings.model.Support;

@Database(entities = {Model.class, Data.class, Support.class}, version = 1, exportSchema = false)
public abstract class ModelDatabase extends RoomDatabase {
    private static ModelDatabase instance;

    public abstract RoomDao roomDao();

    public static synchronized ModelDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ModelDatabase.class, "model_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
