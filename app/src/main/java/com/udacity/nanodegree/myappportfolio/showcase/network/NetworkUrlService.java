package com.udacity.nanodegree.myappportfolio.showcase.network;

import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.ReviewResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
<<<<<<< 2d03e02adc5a1c8622e3cac9ffd7d8e9b7aa56ff
import retrofit2.http.PUT;
=======
>>>>>>> Moviestage2 completed
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bheema on 12/6/16.
 */

public interface NetworkUrlService {


    String SORT_BY_POPULAR = "popular";
    String SORT_BY_RATING = "top_rated";

    @GET("/3/movie/{sort_by}")
    Call<MoviesResponse> getMoviesList(@Path("sort_by") String sortBy, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/reviews")
    Call<ReviewResponse> getReviewsForMovie(@Path("id") int id, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/videos")
    Call<TrailerResponse> getTrailorsForMovie(@Path("id") int id, @Query("api_key") String api_key);


}
