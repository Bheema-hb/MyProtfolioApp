package com.udacity.nanodegree.myappportfolio.showcase.model;

import java.util.List;

/**
 * Created by techjini on 21/3/16.
 */
public class MoviesResponse extends BaseResponse{


    private int page;
    private List<MoviesItem> results;
    private int total_results;
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MoviesItem> getResults() {
        return results;
    }

    public void setResults(List<MoviesItem> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
