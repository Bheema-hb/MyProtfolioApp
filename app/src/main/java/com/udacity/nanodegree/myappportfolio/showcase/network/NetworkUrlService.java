package com.udacity.nanodegree.myappportfolio.showcase.network;

import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bheema on 12/6/16.
 */

public interface NetworkUrlService {

    public static final String SORT_BY_POPULAR = "popular";
    public static final String SORT_BY_RATING = "top_rated";

    @GET("/3/movie/{sort_by}")
    Call<MoviesResponse> getMoviesList(@Path("sort_by") String sortBy, @Query("api_key") String api_key);


}
