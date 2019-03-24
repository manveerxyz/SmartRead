package com.smartread.smartread.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Dao to interact with database at the lowest level
 */
@Dao
public interface ArticleDao {
    @Query("select * from articles")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT * FROM articles WHERE article_id=:id")
    Article getById(int id);

    @Insert(onConflict = IGNORE)
    void insert(Article article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("UPDATE articles set article_fav=:fav where article_id=:id")
    void updateFav(int id, boolean fav);

    @Query("DELETE FROM articles")
    void deleteAll();
}
