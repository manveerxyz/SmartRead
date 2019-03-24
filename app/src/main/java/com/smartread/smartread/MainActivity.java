package com.smartread.smartread;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;

import com.smartread.smartread.db.Article;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_ID = "com.smartread.smartread.MainActivity.extra.id";

    private ArticleViewModel articleViewModel;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArticleListAdapter.ArticleViewHolder viewHolder = (ArticleListAdapter.ArticleViewHolder) view.getTag();

            int id = viewHolder.getId();

            Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
            intent.putExtra(EXTRA_ID, id);
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.article_list);
        final ArticleListAdapter articleAdapter = new ArticleListAdapter(this);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(articleAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        articleAdapter.setOnItemClickListener(onItemClickListener);


        // Get a new or existing ViewModel from the ViewModelProvider.
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        // Add an observer on the LiveData returned by getAllAlarms.
        articleViewModel.getAllArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable final List<Article> articles) {
                // Update the cached copy of the words in the adapter.
                articleAdapter.setArticles(articles);
            }
        });

        final SearchView searchView = findViewById(R.id.search_articles);
        final String[] searchByOptions = getResources().getStringArray(R.array.array_search_by);

        Spinner spinner = findViewById(R.id.search_articles_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchView.setQueryHint(String.format(getString(R.string.search_custom), searchByOptions[i]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
