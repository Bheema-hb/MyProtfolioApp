package com.udacity.nanodegree.myappportfolio.showcase.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Bheema on 12/6/16.
 */

public class NetworkUtility {


    public boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo networkinfo;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkinfo = connectivityManager.getActiveNetworkInfo();

        return (networkinfo != null && networkinfo.isConnected());

    }
}
