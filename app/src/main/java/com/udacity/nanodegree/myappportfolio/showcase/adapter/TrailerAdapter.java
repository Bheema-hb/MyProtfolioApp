package com.udacity.nanodegree.myappportfolio.showcase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.model.TrailerItem;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by techjini on 8/3/16.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ImageViewHolder> {
    private List<TrailerItem> list = Collections.emptyList();
    private OnTrailerClickListener listener;


    public TrailerAdapter(List<TrailerItem> list, OnTrailerClickListener listener) {
        if (list == null) return;
        this.list = list;
        this.listener = listener;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trailer, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        TrailerItem TrailerItem = list.get(position);
        holder.txtName.setText(TrailerItem.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_trailer_name)
        TextView txtName;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.relative)
        public void onMovieClick() {
            listener.onTrailerSelected(list.get(getLayoutPosition()));
        }

    }

    public interface OnTrailerClickListener {
        void onTrailerSelected(TrailerItem item);
    }
}


