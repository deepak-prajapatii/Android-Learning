package com.riseinsteps.androidlearnings.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.riseinsteps.androidlearnings.converters.DataModelConverter;
import com.riseinsteps.androidlearnings.converters.SupportModelConverter;

import java.util.List;

@Entity(tableName = "my_table")
public class Model {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;

    @TypeConverters(DataModelConverter.class)
    private List<Data> data;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @TypeConverters(SupportModelConverter.class)
    private Support support;


    public Model(Integer page, Integer per_page, Integer total, Integer total_pages, List<Data> data, Support support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }

    public List<Data> getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }
}
