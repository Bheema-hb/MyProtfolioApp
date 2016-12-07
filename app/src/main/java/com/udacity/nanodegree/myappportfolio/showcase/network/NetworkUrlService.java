package com.udacity.nanodegree.myappportfolio.showcase.network;

import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.ReviewResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bheema on 12/6/16.
 */

public interface NetworkUrlService {

    public static final String SORT_BY_POPULAR = "popularity.desc";
    public static final String SORT_BY_RATING = "vote_average.desc";

    @GET("/3/discover/movie")
    Call<MoviesResponse> getMoviesList(@Query("sort_by") String sortBy, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/reviews")
    Call<ReviewResponse> getReviewsForMovie(@Path("id") int id, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/videos")
    Call<TrailerResponse> getTrailorsForMovie(@Path("id") int id, @Query("api_key") String api_key);



}
