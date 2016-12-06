package com.udacity.nanodegree.myappportfolio.showcase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.MovieDetailFragment;

/**
 * Created by Bheema on 12/6/16.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchMovieList();
            }
        }, 2000);
    }

    private void launchMovieList() {
        Intent intent = new Intent(SplashActivity.this, MovieListActivity.class);

        startActivity(intent);
        finish();
    }
}
