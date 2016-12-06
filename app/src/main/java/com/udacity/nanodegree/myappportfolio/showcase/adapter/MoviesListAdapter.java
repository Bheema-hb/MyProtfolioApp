package com.udacity.nanodegree.myappportfolio.showcase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.udacity.nanodegree.myappportfolio.BuildConfig;
import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by techjini on 8/3/16.
 */
public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ImageViewHolder> {
    private List<MoviesItem> list = Collections.emptyList();
    private Context context;
    OnMovieClickListener listener;


    public MoviesListAdapter(List<MoviesItem> list, OnMovieClickListener listener) {
        if (list == null) return;
        this.list = list;
        this.listener = listener;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_grid_item_layout, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        MoviesItem moviesItem = list.get(position);

        if (moviesItem.getPoster_path() != null && !moviesItem.getPoster_path().isEmpty()) {
            Glide.with(context).load(BuildConfig.IMG_BASE_URL.concat(moviesItem.getPoster_path())).placeholder(R.drawable.default_pos).into(holder.imgMovie);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.list_item_image)
        ImageView imgMovie;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.list_item_image)
        public void onMovieClick() {
            listener.onMovieSelected(list.get(getLayoutPosition()));
        }

    }

    public interface OnMovieClickListener {
        void onMovieSelected(MoviesItem movieItem);
    }
}


