package com.smartread.smartread.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

/**
 * Backend Database
 */
@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticleDatabase extends RoomDatabase {

    private static ArticleDatabase mINSTANCE;

    @VisibleForTesting
    public static final String DATABASE_NAME = "article-com.smartread.smartread.db";

    public abstract ArticleDao articleModel();

    public static ArticleDatabase getInstance(final Context context) {
        if (mINSTANCE == null) {
            synchronized (ArticleDatabase.class) {
                if (mINSTANCE == null) {
                    mINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ArticleDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration() // TODO Add Proper Migration
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return mINSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     */
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(mINSTANCE).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    /**
     * Populate the database in the background when app is first created.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ArticleDao articleModel;

        PopulateDbAsync(ArticleDatabase db) {
            articleModel = db.articleModel();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Article article = new Article();
            article.title = "Do Owners and Their Dogs Have Similar Personalities?";

            article.source = "Psychology Today";
            article.cred = 88;
            article.fav = false;

            Article article2 = new Article();
            article2.title = "Do Anxious Owners Make for Anxious Dogs?";
            article2.author = "Ray Kidman";
            article2.desc = "A new study explores whether pets and owners share personalities.";
            article2.source = "National Geographic";
            article2.cred = 82;
            article2.fav = false;

            articleModel.insert(article);
            articleModel.insert(article2);
            return null;
        }
    }
}

