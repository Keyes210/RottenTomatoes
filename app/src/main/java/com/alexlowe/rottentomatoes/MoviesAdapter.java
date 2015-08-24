package com.alexlowe.rottentomatoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alex on 8/24/2015.
 */
public class MoviesAdapter extends ArrayAdapter<BoxOfficeMovie> {

    @Bind (R.id.tvTitle)TextView tvTitle;
    @Bind (R.id.tvCriticScore)TextView tvCritics;
    @Bind (R.id.tvCast)TextView tvCast;
    @Bind (R.id.ivPosterImage)ImageView ivPosterImage;

    public MoviesAdapter(Context context, ArrayList<BoxOfficeMovie> adapterMovies){
        super(context, 0, adapterMovies);
    }

    // Translates a particular `BoxOfficeMovie` given a position
    // into a relevant row within an AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BoxOfficeMovie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        // Lookup views within item layout
        ButterKnife.bind(this, convertView);

        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        tvCritics.setText("Score: " + movie.getCriticScore() + "%");
        tvCast.setText(movie.getCastList());

        Picasso.with(getContext()).load(movie.getPosterUrl()).into(ivPosterImage);
        return convertView;
    }
}
