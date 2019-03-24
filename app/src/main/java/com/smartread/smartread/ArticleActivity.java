package com.smartread.smartread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

public class ArticleActivity extends AppCompatActivity {

    private ArticleViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mViewModel = new ArticleViewModel(this.getApplication());

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
