package com.smartread.smartread.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "articles")
public class Article {

    // Class members

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    public int id;

    @ColumnInfo(name = "alarm_title")
    public String title;
    @ColumnInfo(name = "article_source")
    public String source;
    @ColumnInfo(name= "article_cred")
    public int cred;
    @ColumnInfo(name = "article_fav")
    public Boolean fav;


    public Article() {
    }
}
