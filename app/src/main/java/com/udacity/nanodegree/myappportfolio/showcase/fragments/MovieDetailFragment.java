package com.udacity.nanodegree.myappportfolio.showcase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.udacity.nanodegree.myappportfolio.BuildConfig;
import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;
import com.udacity.nanodegree.myappportfolio.showcase.network.NetworkUrlService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailFragment extends BaseFragment {

    public static final String EXTR_MOVIE = "com.udacity.movie.item";
    @Bind(R.id.img_movie)
    ImageView imgMovie;
    @Bind(R.id.txt_release)
    TextView txtRelease;
    @Bind(R.id.txt_rating)
    TextView txtRating;
    @Bind(R.id.txt_title)
    TextView txtTitle;
    private MoviesItem mMovieItem;

    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(Bundle b) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        mMovieItem = getArguments().getParcelable(EXTR_MOVIE);
        if (mMovieItem != null) {
            fillDetailsToViews(mMovieItem);
        }

        return view;
    }

    public void updateUI(MoviesItem item) {
        this.mMovieItem = item;
        if (mMovieItem != null) {

            fillDetailsToViews(mMovieItem);
        }
    }


    private void fillDetailsToViews(MoviesItem item) {

        txtTitle.setText(item.getTitle());
        if (item.getPoster_path() != null && !item.getPoster_path().isEmpty()) {
            Glide.with(getContext()).load(BuildConfig.IMG_BASE_URL.concat(item.getPoster_path())).
                    placeholder(R.drawable.default_pos).into(imgMovie);
        }
        txtRelease.setText(getString(R.string.lbl_release).concat(" " + item.getRelease_date()));
        txtRating.setText(getString(R.string.lbl_rate).concat(" " + String.valueOf(item.getVote_average())).concat("/").concat(getString(R.string.lbl_rating)));


    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
