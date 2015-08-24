package com.alexlowe.rottentomatoes;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alex on 8/24/2015.
 */

//Next, we want to be able to pass the movie object from the list to this detail view, so let's make our BoxOfficeMovie serializable:

public class BoxOfficeMovie implements Serializable{
    private static final long serialVersionUID = -8959832007991513854L;

    private String title, synopsis, posterUrl;
    private int year, criticScore;
    private ArrayList<String> castList;

    private String largePoster;
    private String criticConsensus;
    private int audienceScore;

    // Returns a BoxOfficeMovie given the expected JSON
    // BoxOfficeMovie.fromJson(movieJsonDictionary)
    // Stores the `title`, `year`, `synopsis`, `poster` and `criticsScore`
    public static BoxOfficeMovie fromJson(JSONObject jsonObject){
        BoxOfficeMovie movie = new BoxOfficeMovie();

        try {
            // Deserialize json into object fields
            movie.title = jsonObject.getString("title");
            movie.year = jsonObject.getInt("year");
            movie.synopsis = jsonObject.getString("synopsis");
            movie.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            movie.criticScore = jsonObject.getJSONObject("ratings").getInt("critics_score");

            //for deatail page
            movie.largePoster = jsonObject.getJSONObject("posters").getString("detailed");
            movie.criticConsensus = jsonObject.getString("critics_consensus");
            movie.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");

            // Construct simple array of cast names
            movie.castList = new ArrayList<>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++){
                movie.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return movie;
    }

    // Decodes array of box office movie json results into movie model objects
    // BoxOfficeMovie.fromJson(jsonArrayOfMovies)
    public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArray){
        ArrayList<BoxOfficeMovie> movies = new ArrayList<>(jsonArray.length());

        // Process each result in json array, decode and convert to movie
        // object
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject moviesJson = null;
            try {
                moviesJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

            BoxOfficeMovie movie = BoxOfficeMovie.fromJson(moviesJson);
            if (movie != null){
                movies.add(movie);
            }
        }

        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getYear() {
        return year;
    }

    public int getCriticScore() {
        return criticScore;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }

    public String getCriticConsensus() {
        return criticConsensus;
    }

    public String getLargePoster() {
        return largePoster;
    }

    public int getAudienceScore() {
        return audienceScore;
    }


}
