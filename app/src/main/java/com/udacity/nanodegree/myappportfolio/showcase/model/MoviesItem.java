package com.udacity.nanodegree.myappportfolio.showcase.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by techjini on 21/3/16.
 */
public class MoviesItem implements Parcelable {


    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String poster_path;
    @DatabaseField
    private String overview;
    @DatabaseField
    private String release_date;
    @DatabaseField
    private String title;
    @DatabaseField
    private float vote_average;


    public MoviesItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(id);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeFloat(vote_average);
    }

    public MoviesItem(Parcel in) {

        this.id = in.readInt();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.title = in.readString();
        this.vote_average = in.readFloat();
    }

    public static final Creator<MoviesItem> CREATOR = new Creator<MoviesItem>() {

        @Override
        public MoviesItem createFromParcel(Parcel source) {
            return new MoviesItem(source);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };


}
