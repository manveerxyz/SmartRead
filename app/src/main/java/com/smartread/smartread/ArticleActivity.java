package com.smartread.smartread;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartread.smartread.db.Article;

import net.cachapa.expandablelayout.ExpandableLayout;

public class ArticleActivity extends AppCompatActivity {

    private ArticleViewModel mViewModel;
    private Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_ID, 0);

        mViewModel = new ArticleViewModel(this.getApplication());
        mArticle = mViewModel.getArticleById(id);

        // Initialize Views
        ImageView articleLogoImageView = findViewById(R.id.article_header_source_logo);
        if (mArticle.source.equals(getString(R.string.article_2_source))) { // NE
            articleLogoImageView.setImageResource(R.drawable.ic_natgeo_big_logo);
        } else if (mArticle.source.equals(getString(R.string.article_1_source))) { // PT
            articleLogoImageView.setImageResource(R.drawable.ic_pt_big_logo);
        }
        TextView articleTitleTextView = findViewById(R.id.article_title);
        articleTitleTextView.setText(mArticle.title);

        Button articleAuthorTextView = findViewById(R.id.article_author);
        articleAuthorTextView.setText(mArticle.author);

        TextView articleDescTextView = findViewById(R.id.article_desc);
        articleDescTextView.setText(mArticle.desc);

        TextView articleCredibilityTextView = findViewById(R.id.average_credibility_score_text);
        articleCredibilityTextView.setText(String.format(getString(R.string.average_credibility_score), mArticle.cred));

        Button articleLinkButton = findViewById(R.id.article_header_link);
        articleLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.nationalgeographic.com/animals/2019/03/dogs-and-owners-have-similar-personalities/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        final ExpandableLayout typeSourceLayout = findViewById(R.id.type_source_elayout);
        final ImageView typeSourceArrow = findViewById(R.id.type_source_arrow_button);

        typeSourceLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
            }
        });

        LinearLayout typeSourceButton = findViewById(R.id.type_source_button);
        typeSourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typeSourceLayout.isExpanded()) {
                    typeSourceArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    typeSourceLayout.collapse();
                } else {
                    typeSourceLayout.expand();
                    typeSourceArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
            }
        });

        final ExpandableLayout sourceScoreLayout = findViewById(R.id.source_score_elayout);
        final ImageView sourceScoreArrow = findViewById(R.id.source_score_arrow_button);

        typeSourceLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
            }
        });

        LinearLayout sourceScoreButton = findViewById(R.id.source_score_button);
        sourceScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sourceScoreLayout.isExpanded()) {
                    sourceScoreArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    sourceScoreLayout.collapse();
                } else {
                    sourceScoreLayout.expand();
                    sourceScoreArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
            }
        });

        final ExpandableLayout contentScoreLayout = findViewById(R.id.content_score_elayout);
        final ImageView contentScoreArrow = findViewById(R.id.content_score_arrow_button);

        typeSourceLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
            }
        });

        LinearLayout contentScoreButton = findViewById(R.id.content_score_button);
        contentScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentScoreLayout.isExpanded()) {
                    contentScoreArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    contentScoreLayout.collapse();
                } else {
                    contentScoreLayout.expand();
                    contentScoreArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
            }
        });
    }
}
