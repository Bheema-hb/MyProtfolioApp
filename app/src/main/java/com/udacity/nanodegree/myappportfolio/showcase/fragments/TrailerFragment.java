package com.udacity.nanodegree.myappportfolio.showcase.fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.databinding.LayoutListBinding;
import com.udacity.nanodegree.myappportfolio.showcase.adapter.TrailerAdapter;
import com.udacity.nanodegree.myappportfolio.showcase.model.BaseResponse;
import com.udacity.nanodegree.myappportfolio.showcase.model.TrailerItem;
import com.udacity.nanodegree.myappportfolio.showcase.model.TrailerResponse;
import com.udacity.nanodegree.myappportfolio.showcase.network.MoviesManager;
import com.udacity.nanodegree.myappportfolio.showcase.network.NetworkUtility;

import java.util.ArrayList;

/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class TrailerFragment extends BaseFragment implements MoviesManager.OnCommunicationListener, TrailerAdapter.OnTrailerClickListener {

    private static final String EXTRA_MOVIE_ID = "movie_id_video";
    private MoviesManager mManager = new MoviesManager();
    private int mMovieId = 0;
    private LayoutListBinding mBinding;
    TrailerAdapter mAdapter;


    public TrailerFragment() {
        // Required empty public constructor
    }

    public static TrailerFragment newInstance(int id) {
        TrailerFragment fragment = new TrailerFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_MOVIE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieId = getArguments().getInt(EXTRA_MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_list, container, false);
        mBinding.setSize(1);
        mBinding.setMessage(R.string.msg_no_trailers);
        ArrayList<TrailerItem> item = new ArrayList<>();
        mAdapter = new TrailerAdapter(item, this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mAdapter);
        getData();
        return mBinding.getRoot();
    }

    private void getData() {
        if (NetworkUtility.isConnectionAvailable(getActivity())) {
            showLoadingDialog(R.string.msg_loading);
            mManager.getTrailorsForMovie(this, mMovieId);
        } else {
            showMessageDialog(getString(R.string.no_network),getString(R.string.no_internet_connection));
        }
    }

    @Override
    public void onSuccess(int apiId, BaseResponse response) {
        if (isDetached()) return;
        hideLoadingDialog();
        TrailerResponse response1 = (TrailerResponse) response;
        if (response1 != null) {
            mAdapter = new TrailerAdapter(response1.getResults(), this);
            mBinding.recyclerView.setAdapter(mAdapter);
        }
        mBinding.setSize(mAdapter.getItemCount());
    }

    @Override
    public void onFailure(int apiId, String msg) {
        if (isDetached()) return;
        hideLoadingDialog();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        mBinding.setSize(mAdapter.getItemCount());
    }

    @Override
    public void onException(int apiId, String msg) {
        if (isDetached()) return;
        hideLoadingDialog();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        mBinding.setSize(mAdapter.getItemCount());
    }

    @Override
    public void onTrailerSelected(TrailerItem item) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + item.getKey()));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Log.e("sad","not fo"+ex.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + item.getId()));
            startActivity(intent);
        }
    }
}
