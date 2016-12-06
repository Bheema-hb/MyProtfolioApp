package com.udacity.nanodegree.myappportfolio.showcase.network;

import com.udacity.nanodegree.myappportfolio.BuildConfig;
import com.udacity.nanodegree.myappportfolio.showcase.model.BaseResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;

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


    public interface OnCommunicationListener {
        void onSuccess(int apiId, BaseResponse response);

        void onFailure(int apiId, String msg);

        void onException(int apiId, String msg);
    }


}
