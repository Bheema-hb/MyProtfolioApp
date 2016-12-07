package com.udacity.nanodegree.myappportfolio.showcase.network;

import com.udacity.nanodegree.myappportfolio.BuildConfig;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.ReviewFragment;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.TrailerFragment;
import com.udacity.nanodegree.myappportfolio.showcase.model.BaseResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.ReviewResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by techjini on 14/1/16.
 */
public class MoviesManager {
    public String API_KEY = "812513fb75ffb07daefd072b261911a1";
    private OnCommunicationListener mOnCommunicationListener;
    public static final int API_ID_MOVIE_LIST = 1;
    public static final int API_ID_MOVIE_TRAILOR = 2;
    public static final int API_ID_MOVIE_REVIEW = 3;


    public MoviesManager() {

    }


    public void getMoviesList(OnCommunicationListener listListener, String sortBy) {
        mOnCommunicationListener = listListener;
        NetworkServiceClient apiClient = NetworkServiceClient.getInstance();
        NetworkUrlService networkUrlService = apiClient.getService(NetworkUrlService.class, BuildConfig.URL);
        final Call<MoviesResponse> model = networkUrlService.getMoviesList(sortBy, API_KEY);
        try {
            model.enqueue(new Callback<MoviesResponse>() {

                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    mOnCommunicationListener.onSuccess(API_ID_MOVIE_LIST, response.body());
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    mOnCommunicationListener.onFailure(API_ID_MOVIE_LIST, t.toString());
                }
            });
        } catch (Exception e) {
            mOnCommunicationListener.onException(API_ID_MOVIE_LIST, e.toString());
        }

    }

    public void getReviewsForMovie(OnCommunicationListener listListener, int mMovieId) {
        mOnCommunicationListener = listListener;
        NetworkServiceClient apiClient = NetworkServiceClient.getInstance();
        NetworkUrlService networkUrlService = apiClient.getService(NetworkUrlService.class, BuildConfig.URL);
        Call<ReviewResponse> model = networkUrlService.getReviewsForMovie(mMovieId, API_KEY);
        try {
            model.enqueue(new Callback<ReviewResponse>() {

                @Override
                public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                    mOnCommunicationListener.onSuccess(API_ID_MOVIE_REVIEW, response.body());
                }

                @Override
                public void onFailure(Call<ReviewResponse> call, Throwable t) {
                    mOnCommunicationListener.onFailure(API_ID_MOVIE_REVIEW, t.toString());
                }

            });
        } catch (Exception e) {
            mOnCommunicationListener.onException(API_ID_MOVIE_REVIEW, e.toString());
        }
    }

    public void getTrailorsForMovie(OnCommunicationListener listener, int mMovieId) {
        mOnCommunicationListener = listener;
        NetworkServiceClient apiClient = NetworkServiceClient.getInstance();
        NetworkUrlService networkUrlService = apiClient.getService(NetworkUrlService.class, BuildConfig.URL);
        Call<TrailerResponse> model = networkUrlService.getTrailorsForMovie(mMovieId, API_KEY);
        try {
            model.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    mOnCommunicationListener.onSuccess(API_ID_MOVIE_TRAILOR, response.body());
                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {
                    mOnCommunicationListener.onFailure(API_ID_MOVIE_TRAILOR, t.toString());
                }


            });
        } catch (Exception e) {
            mOnCommunicationListener.onException(API_ID_MOVIE_TRAILOR, e.toString());
        }
    }


    public interface OnCommunicationListener {
        void onSuccess(int apiId, BaseResponse response);

        void onFailure(int apiId, String msg);

        void onException(int apiId, String msg);
    }


}
