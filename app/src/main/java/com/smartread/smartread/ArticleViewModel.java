package com.smartread.smartread;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.smartread.smartread.db.Article;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * View Model to keep a reference to the article repository and
 * an up-to-date list of all article.
 * Completely separates UI from Repository
 */
public class ArticleViewModel extends AndroidViewModel {

    private final String TAG = "ArticleViewModel";

    private ArticleRepository mRepository;
    private LiveData<List<Article>> mAllArticles;

    public ArticleViewModel(Application application) {
        super(application);
        mRepository = new ArticleRepository(application);
        mAllArticles = mRepository.getAllArticles();
    }

    // List is wrapped in LiveData in order to be observed and updated efficiently
    public LiveData<List<Article>> getAllArticles() {
        return mAllArticles;
    }

    public void insert(Article article) {
        mRepository.insert(article);
    }
    

    public void update(Article article) {
        mRepository.update(article);
    }

    public void updateFav(Article article) {
        mRepository.updateFav(article);
    }

    public void delete(Article article) {
        mRepository.delete(article);
    }

    public Article getArticleById(int id) {
        try {
            return mRepository.getArticleById(id);
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, "Error when retrieving article by id: " + id);
            e.printStackTrace();
        }
        return new Article();
    }

}
