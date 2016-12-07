package com.udacity.nanodegree.myappportfolio.showcase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
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
import com.udacity.nanodegree.myappportfolio.showcase.NanodegreeApplication;
import com.udacity.nanodegree.myappportfolio.showcase.adapter.MovieTabAdapter;
import com.udacity.nanodegree.myappportfolio.showcase.database.MovieDbHelper;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class MovieDetailFragment extends BaseFragment {

    public static final String EXTR_MOVIE = "selected_movie";
    @Bind(R.id.img_movie)
    ImageView imgMovie;
    @Bind(R.id.txt_release)
    TextView txtRelease;
    @Bind(R.id.txt_rating)
    TextView txtRating;
    @Bind(R.id.txt_title)
    TextView txtTitle;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;


    private Menu mMenu;

    private MoviesItem mMovieItem;
    private OnFragmentInteractionListener mListener;
    private boolean isAddedToFav;

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
            setHasOptionsMenu(true);
            viewPager.setAdapter(new MovieTabAdapter(getChildFragmentManager(), getActivity(), mMovieItem));
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setupWithViewPager(viewPager);
        }

        return view;
    }


    public void updateUI(MoviesItem item) {
        this.mMovieItem = item;
        if (mMovieItem != null) {

            fillDetailsToViews(mMovieItem);
            setHasOptionsMenu(true);
            viewPager.setAdapter(new MovieTabAdapter(getChildFragmentManager(), getActivity(), mMovieItem));
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setupWithViewPager(viewPager);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mMenu = menu;
        inflater.inflate(R.menu.menu_favourite, menu);
        if (MovieDbHelper.isExists(NanodegreeApplication.getInstance().getDBHepler(), mMovieItem.getId())) {
            isAddedToFav = true;
            mMenu.findItem(R.id.action_fav).setIcon(android.R.drawable.btn_star_big_on);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isAddedToFav) {
            removeFromFav(mMovieItem);
        } else {

            addToFav(mMovieItem);
        }
        if (mListener != null) {
            mListener.onFavClick(mMovieItem, isAddedToFav);
        }

        return super.onOptionsItemSelected(item);
    }

    private void addToFav(MoviesItem item) {
        MovieDbHelper.addMovie(NanodegreeApplication.getInstance().getDBHepler(), item);
        isAddedToFav = true;
        mMenu.findItem(R.id.action_fav).setIcon(android.R.drawable.btn_star_big_on);
        Toast.makeText(getActivity(), getString(R.string.added_to_fav), Toast.LENGTH_LONG).show();

    }

    private void removeFromFav(MoviesItem item) {
        MovieDbHelper.removeMovie(NanodegreeApplication.getInstance().getDBHepler(), item.getId());
        isAddedToFav = false;
        mMenu.findItem(R.id.action_fav).setIcon(android.R.drawable.btn_star_big_off);
        Toast.makeText(getActivity(), getString(R.string.removed_fav), Toast.LENGTH_LONG).show();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
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
    public interface OnFragmentInteractionListener {
        void onFavClick(MoviesItem item, boolean isFav);
    }



}
