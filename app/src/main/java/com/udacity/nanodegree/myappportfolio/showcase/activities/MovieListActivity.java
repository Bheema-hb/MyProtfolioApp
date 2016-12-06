package com.udacity.nanodegree.myappportfolio.showcase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.MovieDetailFragment;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.MovieListFragment;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bheema on 12/6/16.
 */

public class MovieListActivity extends BaseActivity implements MovieListFragment.OnFragmentInteractionListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        MovieListFragment listFragment = new MovieListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.movie_list_container,
                listFragment).commit();
    }

    @Override
    public void onMovieItemClick(MoviesItem item) {
        Intent intent = new Intent(MovieListActivity.this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailFragment
                .EXTR_MOVIE, item);
        startActivity(intent);
    }
}
