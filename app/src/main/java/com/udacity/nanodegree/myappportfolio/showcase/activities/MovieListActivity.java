package com.udacity.nanodegree.myappportfolio.showcase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.BaseFragment;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.MovieDetailFragment;
import com.udacity.nanodegree.myappportfolio.showcase.fragments.MovieListFragment;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bheema on 12/6/16.
 */

public class MovieListActivity extends BaseActivity implements MovieListFragment.OnFragmentInteractionListener, MovieDetailFragment.OnFragmentInteractionListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private MovieListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        listFragment = new MovieListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.movie_list_container,
                listFragment).commit();
    }

    @Override
    public void onMovieItemClick(MoviesItem item) {
        Bundle b = new Bundle();
        b.putParcelable(MovieDetailFragment
                .EXTR_MOVIE, item);
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(b);
        addSubFragment(movieDetailFragment, "MovieDetail");
    }

    @Override
    public void onFavClick(MoviesItem item, boolean isFav) {
        if (listFragment != null && !listFragment.isDetached()) {
            listFragment.updateFav();
        }
    }

    public void addSubFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.movie_list_container, fragment, tag).hide(listFragment).addToBackStack(tag).commit();
        setTitle(getString(R.string.title_details));
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.movie_list_container);
        if (fragment != null) {
            if (fragment instanceof MovieDetailFragment) {
                if(listFragment!=null) {
                    setTitle(listFragment.getScreenTitle());
                }
            }
        }
        super.onBackPressed();
    }


}
