package com.smartread.smartread;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartread.smartread.db.Article;

import java.util.Collections;
import java.util.List;

/**
 * ArrayAdapter used to populate MainActivity Alarms ListView
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    private final String TAG = "ArticleListAdapter";

    /**
     * View for each alarm
     */
    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final TextView articleHeaderTextView;
        private final TextView articleSourceTextView;
        private final TextView articleCredibilityTextView;
        private final ImageView articleFavButton;
        private final ImageView articleLogoImageView;
        private int id;

        private ArticleViewHolder(View itemView) {
            super(itemView);
            articleHeaderTextView = itemView.findViewById(R.id.item_article_header);
            articleSourceTextView = itemView.findViewById(R.id.item_article_source);
            articleCredibilityTextView = itemView.findViewById(R.id.item_credibility);
            articleFavButton = itemView.findViewById(R.id.item_fav_button);
            articleLogoImageView = itemView.findViewById(R.id.item_logo);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public int getId() {
            return id;
        }
    }



    // Layout members
    private final LayoutInflater mInflater;
    private final TextView mEmptyTextView;
    // Data list (cached copy of alarms)
    private List<Article> mArticles = Collections.emptyList();
    // To handle interactions with database
    private ArticleViewModel mArticleViewModel;
    private Context mContext;
    private View.OnClickListener mOnItemClickListener;

    private Article mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;


    public ArticleListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mEmptyTextView = ((MainActivity) context).findViewById(R.id.empty_articles_text);
        mContext = context;

        mArticleViewModel = ViewModelProviders.of((MainActivity) context).get(ArticleViewModel.class);

        setHasStableIds(true); // so Switch interaction has smooth animations
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_article_header, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder viewHolder, int position) {
        Resources resources = viewHolder.itemView.getContext().getResources();

        Article article = mArticles.get(position);

        viewHolder.id = article.id;
        viewHolder.articleHeaderTextView.setText(article.title);
        viewHolder.articleSourceTextView.setText(article.source);
        viewHolder.articleCredibilityTextView.setText(String.format("%d%%", article.cred));

        if (position == 0) {
            viewHolder.articleLogoImageView.setImageResource(R.mipmap.ic_pt);
        } else {
            viewHolder.articleLogoImageView.setImageResource(R.mipmap.ic_natgeo);
        }

        // Add Button click listeners
        addSwitchListener(viewHolder, resources);
//        addEditButtonListener(alarm, viewHolder);
    }


    @Override
    public int getItemCount() {
        mEmptyTextView.setVisibility(mArticles.size() > 0 ? View.GONE : View.VISIBLE);
        return mArticles.size();
    }

    public void setArticles(List<Article> articles) {
        Log.i(TAG, "updating alarms data-set");
        this.mArticles = articles;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public void deleteItem(int position) {
        mRecentlyDeletedItem = mArticles.get(position);
        mRecentlyDeletedItemPosition = position;
        mArticles.remove(position);
        notifyItemRemoved(position);
        showUndoSnackbar();
    }

    private void showUndoSnackbar() {
        View view = ((MainActivity) mContext).findViewById(R.id.main_layout);
        Snackbar snackbar = Snackbar.make(view, R.string.deleted,
                Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.undo, new UndoListener());
        snackbar.show();
    }

    public class UndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            mArticles.add(mRecentlyDeletedItemPosition,
                    mRecentlyDeletedItem);
            notifyItemInserted(mRecentlyDeletedItemPosition);
        }
    }

    @Override
    public long getItemId(int position) {
        return mArticles.get(position).id;
    }

    /**
     * Add OnCheckedChange Listener to alarm's "active" switch
     *
     * @param viewHolder Alarm's ViewHolder object, containing Switch
     * @param resources  Resources file to get color values from
     */
    private void addSwitchListener(final ArticleViewHolder viewHolder, final Resources resources) {
        viewHolder.articleFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.articleFavButton.getTag() != "bg") {
                    viewHolder.articleFavButton.setImageResource(R.drawable.ic_star_black_24dp);
                    viewHolder.articleFavButton.setTag("bg");
                } else {
                    viewHolder.articleFavButton.setImageResource(R.drawable.ic_star_border_black_24dp);
                    viewHolder.articleFavButton.setTag("nbg");
                }
            }
        });
    }
//
//    /**
//     * Add OnClickListener to alarm's edit button to open EditAlarmActivity (AddAlarmActivity.java)
//     *
//     * @param alarm      Alarm object
//     * @param viewHolder Alarm's ViewHolder object, containing edit button
//     */
//    private void addEditButtonListener(final Alarm alarm, final ArticleViewHolder viewHolder) {
//        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, AddAlarmActivity.class);
//
//                Bundle args = new Bundle();
//                args.putParcelable(AddAlarmActivity.EXTRA_ALARM, alarm);
//                intent.putExtra(AddAlarmActivity.EXTRA_BUNDLE, args);
//
//                ((MainActivity) context).startActivityForResult(intent, MainActivity.EDIT_ALARM_ACTIVITY_REQUEST_CODE);
//            }
//        });
//    }

}
