package com.alexlowe.rottentomatoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind (R.id.ivPosterImage) ImageView ivPoster;
    @Bind (R.id.tvTitle) TextView tvTitle;
    @Bind (R.id.tvSynopsis) TextView tvSynopsis;
    @Bind (R.id.tvCast) TextView tvCast;
    @Bind (R.id.tvAudienceScore) TextView tvAudience;
    @Bind (R.id.tvCriticsScore) TextView tvCritics;
    @Bind (R.id.tvCriticsConsensus) TextView tvConsensus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // Use the movie to populate the data into our views
        BoxOfficeMovie movie = (BoxOfficeMovie) getIntent()
                .getSerializableExtra(BoxOfficeActivity.MOVIE_DETAIL_KEY);
        loadMovie(movie);
    }

    private void loadMovie(BoxOfficeMovie movie) {
        // Populate data
        tvTitle.setText(movie.getTitle());
        tvCritics.setText(Html.fromHtml("<b>Critics Score:</b> " + movie.getCriticScore() + "%"));
        tvAudience.setText(Html.fromHtml("<b>Audience Score:</b> " + movie.getAudienceScore() + "%"));
        tvCast.setText(movie.getCastList());
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getSynopsis()));
        tvConsensus.setText(Html.fromHtml("<b>Consensus:</b> " + movie.getCriticConsensus()));

        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(this).load(movie.getLargePoster()).placeholder(R.drawable.large_poster)
                .into(ivPoster);

    }

}
