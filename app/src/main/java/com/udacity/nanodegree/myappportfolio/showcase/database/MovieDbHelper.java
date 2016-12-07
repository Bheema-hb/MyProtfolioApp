/**
 *
 */
package com.udacity.nanodegree.myappportfolio.showcase.database;

import com.j256.ormlite.dao.Dao;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class MovieDbHelper {


    public static boolean removeMovie(DatabaseHelper helper, int id) {

        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            companyDao.deleteById(id);
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

        return true;
    }

    public static boolean isExists(DatabaseHelper helper, int id) {

        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            return companyDao.idExists(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }


    public static boolean addMovie(DatabaseHelper helper, MoviesItem item) {

        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            companyDao.createOrUpdate(item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

        return true;
    }

    public static List<MoviesItem> getMovieList(DatabaseHelper helper) {
        List<MoviesItem> moviesItems = new ArrayList<>();
        try {
            final Dao<MoviesItem, Integer> companyDao = helper.getMoviesDao();
            moviesItems = companyDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();


        }

        return moviesItems;
    }


}
