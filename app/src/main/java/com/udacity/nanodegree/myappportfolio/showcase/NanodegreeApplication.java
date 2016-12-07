package com.udacity.nanodegree.myappportfolio.showcase;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.udacity.nanodegree.myappportfolio.showcase.database.DatabaseHelper;


/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class NanodegreeApplication extends Application {

    private static NanodegreeApplication sIntance;

    private DatabaseHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sIntance = this;
    }

    public static NanodegreeApplication getInstance() {
        return sIntance;
    }


    public DatabaseHelper getDBHepler() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dbHelper;
    }


    public void closeDB() {
        if (dbHelper != null) {
            dbHelper.close();
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

}
