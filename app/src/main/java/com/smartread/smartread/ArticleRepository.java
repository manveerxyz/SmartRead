package com.smartread.smartread;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.smartread.smartread.db.Article;
import com.smartread.smartread.db.ArticleDao;
import com.smartread.smartread.db.ArticleDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Abstracted Repository to handle interactions between ViewModels and Database
 */
public class ArticleRepository {

    private ArticleDao mArticleModel;
    private LiveData<List<Article>> mAllArticles;

    public ArticleRepository(Application application) {
        // Application is used instead of Context in order to prevent memory leaks
        // between Activity switches
        ArticleDatabase db = ArticleDatabase.getInstance(application);
        mArticleModel = db.articleModel();
        mAllArticles = mArticleModel.getAllArticles();
    }

    // Observed LiveData will notify the observer when data has changed
    public LiveData<List<Article>> getAllArticles() {
        return mAllArticles;
    }

    public void insert(Article article) {
        new insertAsyncTask(mArticleModel).execute(article);
    }


    public void update(Article article) {
        new updateAsyncTask(mArticleModel).execute(article);
    }

    public void updateActive(Article article) {
        new updateFavAsyncTask(mArticleModel).execute(article);
    }

    public void delete(Article article) {
        new deleteAsyncTask(mArticleModel).execute(article);
    }

    public Article getArticleById(int id) throws ExecutionException, InterruptedException {
        return new getByIdAsyncTask(mArticleModel).execute(id).get();
    }


    /*
     * Asynchronous Tasks
     *
     * One for each interaction with database.
     * All classes are static to prevent memory leaks.
     */

    private static class insertAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleDao articleModel;

        insertAsyncTask(ArticleDao articleModel) {
            this.articleModel = articleModel;
        }

        @Override
        protected Void doInBackground(final Article... params) {
            articleModel.insert(params[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleDao articleModel;

        updateAsyncTask(ArticleDao articleModel) {
            this.articleModel = articleModel;
        }

        @Override
        protected Void doInBackground(final Article... params) {
            articleModel.update(params[0]);
            return null;
        }
    }

    private static class updateFavAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleDao articleModel;

        updateFavAsyncTask(ArticleDao articleModel) {
            this.articleModel = articleModel;
        }

        @Override
        protected Void doInBackground(final Article... params) {
            articleModel.updateFav(params[0].id, params[0].fav);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleDao articleModel;

        deleteAsyncTask(ArticleDao articleModel) {
            this.articleModel = articleModel;
        }

        @Override
        protected Void doInBackground(final Article... params) {
            articleModel.delete(params[0]);
            return null;
        }
    }

    private static class getByIdAsyncTask extends android.os.AsyncTask<Integer, Void, Article> {

        private ArticleDao articleModel;

        getByIdAsyncTask(ArticleDao articleModel) {
            this.articleModel = articleModel;
        }

        @Override
        protected Article doInBackground(final Integer... params) {
            return articleModel.getById(params[0]);
        }
    }
}
