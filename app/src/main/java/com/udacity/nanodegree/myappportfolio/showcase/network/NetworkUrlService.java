package com.udacity.nanodegree.myappportfolio.showcase.network;

import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Bheema on 12/6/16.
 */

public interface NetworkUrlService {

    public static final String SORT_BY_POPULAR = "popularity.desc";
    public static final String SORT_BY_RATING = "vote_average.desc";

    @GET("/3/discover/movie")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<MoviesResponse> getMoviesList(@Query("sort_by") String sortBy, @Query("api_key") String api_key);



}
