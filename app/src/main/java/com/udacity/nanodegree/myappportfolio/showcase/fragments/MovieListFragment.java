package com.udacity.nanodegree.myappportfolio.showcase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.adapter.MoviesListAdapter;
import com.udacity.nanodegree.myappportfolio.showcase.model.BaseResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;
import com.udacity.nanodegree.myappportfolio.showcase.network.MoviesManager;
import com.udacity.nanodegree.myappportfolio.showcase.network.NetworkUrlService;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bheema on 12/6/16.
 */

public class MovieListFragment extends BaseFragment implements MoviesManager.OnCommunicationListener, MoviesListAdapter.OnMovieClickListener {
    @Bind(R.id.movie_list_recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.textNoMovies)
    TextView txtEmpty;
    OnFragmentInteractionListener mListener;
    private final int MOVIE_POPULAR = 1;
    private final int MOVIE_RATED = 2;
    private int selectedMenu;
    private MoviesManager mMoviesManager;
    private MoviesListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_list_frag_layout, container,false);
        ButterKnife.bind(this, view);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMoviesManager = new MoviesManager();
        getMoviesList(MOVIE_POPULAR);
        selectedMenu = MOVIE_POPULAR;
        getActivity().setTitle(R.string.title_movie_list_pop);
        setHasOptionsMenu(true);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void getMoviesList(int movie_popular) {
        showLoadingDialog(R.string.msg_loading);
        mMoviesManager.getMoviesList(MovieListFragment.this, NetworkUrlService.SORT_BY_POPULAR);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movies_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            selectedMenu = MOVIE_POPULAR;
            showLoadingDialog(R.string.msg_loading);
            getActivity().setTitle(R.string.title_movie_list_pop);
            mMoviesManager.getMoviesList(MovieListFragment.this, NetworkUrlService.SORT_BY_POPULAR);
            return true;
        } else if (id == R.id.action_rating) {
            selectedMenu = MOVIE_RATED;
            showLoadingDialog(R.string.msg_loading);
            getActivity().setTitle(R.string.title_movie_list_rate);
            mMoviesManager.getMoviesList(MovieListFragment.this, NetworkUrlService.SORT_BY_RATING);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(int apiId, BaseResponse response) {

        hideLoadingDialog();
        if (apiId == MoviesManager.API_ID_MOVIE_LIST) {
            MoviesResponse res = (MoviesResponse) response;
            if (res.getResults() == null || res.getResults().size() == 0) {
                txtEmpty.setVisibility(View.VISIBLE);
            } else {
                setMovieListView(res.getResults());
            }
        }

    }

    @Override
    public void onFailure(int apiId, String msg) {
        hideLoadingDialog();
    }

    @Override
    public void onException(int apiId, String msg) {
        hideLoadingDialog();
    }


    private void setMovieListView(List<MoviesItem> items) {
        txtEmpty.setVisibility(View.GONE);
        adapter = new MoviesListAdapter(items, MovieListFragment.this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onMovieSelected(MoviesItem movieItem) {
        mListener.onMovieItemClick(movieItem);
    }


    public interface OnFragmentInteractionListener {
        void onMovieItemClick(MoviesItem item);
    }
}
