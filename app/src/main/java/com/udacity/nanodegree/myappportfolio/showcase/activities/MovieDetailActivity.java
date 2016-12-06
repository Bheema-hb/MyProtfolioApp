package com.udacity.nanodegree.myappportfolio.showcase.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.MovieDetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        setTitle(R.string.title_details);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                MovieDetailFragment.newInstance(getIntent().getExtras())).commit();
        getSupportFragmentManager().executePendingTransactions();
    }


}
