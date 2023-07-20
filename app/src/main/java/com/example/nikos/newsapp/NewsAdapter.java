package com.example.nikos.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /** Tag for the log messages */
    public static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> news) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the news at the given position
     * in the list of news.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        NewsAdapter.ViewHolder holder;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);
        }else{
            holder = (ViewHolder) listItemView.getTag();
        }

        // Get the {@link News} object located at this position in the list
        News currentResult = getItem(position);


        // Find the TextView with view ID title and hide it, if it is empty
        if (currentResult.getTitle().isEmpty()) holder.titleTextView.setVisibility(View.GONE);
        else {
            holder.titleTextView.setText(currentResult.getTitle());
            holder.titleTextView.setVisibility(View.VISIBLE);
        }

        // Find the TextView with view ID section_name
        if (currentResult.getSectionName().isEmpty())
            holder.sectionNameTextView.setVisibility(View.GONE);
        else {
            holder.sectionNameTextView.setText(currentResult.getSectionName());
            holder.sectionNameTextView.setVisibility(View.VISIBLE);
        }

        // Find the TextView with view ID publication_date
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate=formatDate(currentResult.getPublicationDate());


        // Display the date of the current result in the TextView
        holder.publicationDateTextView.setText(formattedDate);
        holder.publicationDateTextView.setVisibility(View.VISIBLE);


        // Find the TextView with view ID author. It can't be hided as next to this view is
        // publication date view and we don't want to be in the same line with title
        if (currentResult.getAuthors().isEmpty()) holder.authorsTextView.setVisibility(View.GONE);
        else {
            holder.authorsTextView.setText(currentResult.getAuthors());
            holder.authorsTextView.setVisibility(View.VISIBLE);
        }

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


    /**
     * Return the formatted date string dd/mm/yyyy from a String yyyy-mm-dd.
     */
    private String formatDate(String inputStringDate) {
        inputStringDate = inputStringDate.substring(0, inputStringDate.length() - 1);
        String oldFormat = "yyyy-MM-dd'T'HH:mm:ss";
        String newFormat = "LLL MM,YYYY";
        SimpleDateFormat inputFormat = new SimpleDateFormat(oldFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(newFormat);
        Date date = null;
        String output = "";
        try {
            date = inputFormat.parse(inputStringDate);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "DateTime parse exception: " + e);
        }
        return output;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView titleTextView;

        @BindView(R.id.authors)
        TextView authorsTextView;

        @BindView(R.id.section_name)
        TextView sectionNameTextView;

        @BindView(R.id.publication_date)
        TextView publicationDateTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
