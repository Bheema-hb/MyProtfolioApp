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
import com.udacity.nanodegree.myappportfolio.showcase.NanodegreeApplication;
import com.udacity.nanodegree.myappportfolio.showcase.activities.MovieListActivity;
import com.udacity.nanodegree.myappportfolio.showcase.adapter.MoviesListAdapter;
import com.udacity.nanodegree.myappportfolio.showcase.database.MovieDbHelper;
import com.udacity.nanodegree.myappportfolio.showcase.model.BaseResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesResponse;
import com.udacity.nanodegree.myappportfolio.showcase.network.MoviesManager;
import com.udacity.nanodegree.myappportfolio.showcase.network.NetworkUrlService;
import com.udacity.nanodegree.myappportfolio.showcase.network.NetworkUtility;

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
    private final int MOVIE_FAV = 3;
    private int selectedMenu;
    private MoviesManager mMoviesManager;
    private MoviesListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_list_frag_layout, container, false);
        ButterKnife.bind(this, view);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMoviesManager = new MoviesManager();
        getMoviesList(MOVIE_POPULAR);
        selectedMenu = MOVIE_POPULAR;
        ((MovieListActivity) getActivity()).setTitle(R.string.title_movie_list_pop);
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
            if (NetworkUtility.isConnectionAvailable(getActivity())) {
                selectedMenu = MOVIE_POPULAR;
                showLoadingDialog(R.string.msg_loading);
                ((MovieListActivity) getActivity()).setTitle(getString(R.string.title_movie_list_pop));
                mMoviesManager.getMoviesList(MovieListFragment.this, NetworkUrlService.SORT_BY_POPULAR);
            } else {
                showMessageDialog(getString(R.string.no_network), getString(R.string.no_internet_connection));
            }
            return true;
        } else if (id == R.id.action_rating) {
            if (NetworkUtility.isConnectionAvailable(getActivity())) {
                selectedMenu = MOVIE_RATED;
                showLoadingDialog(R.string.msg_loading);
                ((MovieListActivity) getActivity()).setTitle(getString(R.string.title_movie_list_rate));
                mMoviesManager.getMoviesList(MovieListFragment.this, NetworkUrlService.SORT_BY_RATING);
            } else {
                showMessageDialog(getString(R.string.no_network), getString(R.string.no_internet_connection));
            }
            return true;
        } else if (id == R.id.action_fav_list) {
            if (NetworkUtility.isConnectionAvailable(getActivity())) {

                selectedMenu = MOVIE_FAV;
                ((MovieListActivity) getActivity()).setTitle(getString(R.string.title_movie_fav));
                List<MoviesItem> items = MovieDbHelper.getMovieList(NanodegreeApplication.getInstance().getDBHepler());
                setMovieListView(items);
            } else {
                showMessageDialog(getString(R.string.no_network), getString(R.string.no_internet_connection));
            }
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
        showMessageDialog(getString(R.string.error), getString(R.string.unable_to_fetch_data));
    }

    @Override
    public void onException(int apiId, String msg) {
        hideLoadingDialog();
        showMessageDialog(getString(R.string.error), getString(R.string.unable_to_fetch_data));

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

    public String getScreenTitle() {
        String title = "";
        switch (selectedMenu) {
            case MOVIE_FAV:
                title = getString(R.string.title_movie_fav);
                break;
            case MOVIE_POPULAR:
                title = getString(R.string.title_movie_list_pop);
                break;
            case MOVIE_RATED:
                title = getString(R.string.title_movie_list_rate);
                break;
        }
        return title;
    }


    public interface OnFragmentInteractionListener {
        void onMovieItemClick(MoviesItem item);
    }

    public void updateFav() {
        if (selectedMenu == MOVIE_FAV) {
            getActivity().setTitle(R.string.title_movie_fav);
            List<MoviesItem> items = MovieDbHelper.getMovieList(NanodegreeApplication.getInstance().getDBHepler());
            setMovieListView(items);
        }

    }
}
