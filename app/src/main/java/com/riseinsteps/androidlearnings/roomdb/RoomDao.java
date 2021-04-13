package com.riseinsteps.androidlearnings.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.riseinsteps.androidlearnings.model.Model;

@Dao
public interface RoomDao {

    @Insert
    void insert(Model model);

    @Query("SELECT * FROM my_table")
    public Model getModel();
}
