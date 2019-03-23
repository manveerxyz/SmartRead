package com.smartread.smartread;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Collections;

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

        private ArticleViewHolder(View itemView) {
            super(itemView);
            articleHeaderTextView = itemView.findViewById(R.id.item_article_header);
            articleSourceTextView = itemView.findViewById(R.id.item_article_source);
            articleCredibilityTextView = itemView.findViewById(R.id.item_credibility);
        }
    }

    // Layout members
    private final LayoutInflater mInflater;
    private final TextView mEmptyTextView;
    // Data list (cached copy of alarms)
//    private List<Alarm> mAlarms = Collections.emptyList();
    private int mArticleCount = 0;

    public ArticleListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mEmptyTextView = ((MainActivity) context).findViewById(R.id.empty_articles_text);

        setHasStableIds(true); // so Switch interaction has smooth animations
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

        mArticleCount++;

        if (position == 0) {
            viewHolder.articleHeaderTextView.setText(R.string.article_1_header);
            viewHolder.articleSourceTextView.setText(R.string.article_1_source);
            viewHolder.articleCredibilityTextView.setText(R.string.article_1_credibility);
        } else {
            viewHolder.articleHeaderTextView.setText(R.string.article_2_header);
            viewHolder.articleSourceTextView.setText(R.string.article_2_source);
            viewHolder.articleCredibilityTextView.setText(R.string.article_2_credibility);
        }



        // Add Button click listeners
//        addSwitchListener(alarm, viewHolder, resources);
//        addEditButtonListener(alarm, viewHolder);
    }


    @Override
    public int getItemCount() {
        mEmptyTextView.setVisibility(mArticleCount > 0 ? View.GONE : View.VISIBLE);
        return 2;
    }

//    @Override
//    public long getItemId(int position) {
//        return mAlarms.get(position).id;
//    }
//
//    /**
//     * Add OnCheckedChange Listener to alarm's "active" switch
//     *
//     * @param alarm      Alarm object
//     * @param viewHolder Alarm's ViewHolder object, containing Switch
//     * @param resources  Resources file to get color values from
//     */
//    private void addSwitchListener(final Alarm alarm, final ArticleViewHolder viewHolder, final Resources resources) {
//        viewHolder.activeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//                if (checked) {
//                    alarm.setActive(true);
//                    viewHolder.timeTextView.setTextColor(resources.getColor(R.color.colorAccent));
//                    viewHolder.repetitionTextView.setTextColor(resources.getColor(R.color.colorDarkText));
//                    // schedule alarm
//                    Log.i(TAG, "scheduling alarm: " + alarm.id);
//                    mAlarmHandler.scheduleAlarm(alarm);
//                } else {
//                    alarm.setActive(false);
//                    viewHolder.timeTextView.setTextColor(resources.getColor(R.color.colorGrey500));
//                    viewHolder.repetitionTextView.setTextColor(resources.getColor(R.color.colorGrey500));
//                    mAlarmHandler.cancelAlarm(alarm);
//                }
//
//                // Update database and schedule alarm
//                Log.i(TAG, "updating database with alarm: " + alarm.id);
//                mAlarmViewModel.updateActive(alarm);
//            }
//        });
//    }
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
