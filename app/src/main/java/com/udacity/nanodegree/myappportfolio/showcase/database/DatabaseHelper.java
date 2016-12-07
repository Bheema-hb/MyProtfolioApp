package com.udacity.nanodegree.myappportfolio.showcase.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.udacity.nanodegree.myappportfolio.showcase.model.MoviesItem;

import java.sql.SQLException;


/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<MoviesItem, Integer> moviesDao = null;
    private RuntimeExceptionDao<MoviesItem, Integer> simpleRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MoviesItem.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<MoviesItem, Integer> getMoviesDao() throws SQLException {
        if (moviesDao == null) {
            moviesDao = getDao(MoviesItem.class);
        }
        return moviesDao;
    }


    @Override
    public void close() {
        super.close();
        moviesDao = null;
    }
}

