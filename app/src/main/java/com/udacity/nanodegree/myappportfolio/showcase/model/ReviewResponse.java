package com.udacity.nanodegree.myappportfolio.showcase.model;

import java.util.List;

/**
 * Created by techjini on 21/3/16.
 */
public class ReviewResponse extends BaseResponse {


    private int id;
    private List<ReviewItem> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReviewItem> getResults() {
        return results;
    }

    public void setResults(List<ReviewItem> results) {
        this.results = results;
    }
}
