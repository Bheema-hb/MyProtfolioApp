package com.udacity.nanodegree.myappportfolio.showcase.model;

import java.util.List;

/**
 * Created by techjini on 21/3/16.
 */
public class TrailerResponse extends BaseResponse{


    private int  id;
    private List<TrailerItem> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TrailerItem> getResults() {
        return results;
    }

    public void setResults(List<TrailerItem> results) {
        this.results = results;
    }
}
