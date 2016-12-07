package com.udacity.nanodegree.myappportfolio.showcase.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.nanodegree.myappportfolio.R;

/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class OverViewFragment extends BaseFragment {

    private static final String EXTRA_MOVIE_OVERVIEW = "movie_id_overview";
    private String mMovieOverview = "";

    public OverViewFragment() {
        // Required empty public constructor
    }

    public static OverViewFragment newInstance(String overview) {
        OverViewFragment fragment = new OverViewFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_MOVIE_OVERVIEW, overview);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieOverview = getArguments().getString(EXTRA_MOVIE_OVERVIEW);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  =inflater.inflate(R.layout.fragment_overview, container, false);
        TextView txtOverView=(TextView)rootView.findViewById(R.id.txt_overview);
        txtOverView.setText(mMovieOverview);
        return rootView;
    }


}
