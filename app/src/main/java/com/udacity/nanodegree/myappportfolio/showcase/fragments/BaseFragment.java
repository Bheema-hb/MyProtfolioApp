package com.udacity.nanodegree.myappportfolio.showcase.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Bheema on 12/6/16.
 */

public class BaseFragment extends Fragment {
    private ProgressDialog mProgressDialog;
    AlertDialog alertDialog;

    public synchronized void showLoadingDialog(int resId) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(resId));
            mProgressDialog.show();
        } else {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

    public synchronized void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.cancel();
            mProgressDialog = null;
        }

    }


    public void showDialog(DialogInterface.OnClickListener okClickListener, DialogInterface.OnClickListener cancelListener, String okText, String cancelText, String title, String message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(okText, okClickListener);
        if (cancelListener != null) {
            alertDialogBuilder.setNegativeButton(cancelText, cancelListener);
        }
        // create alert dialog
        alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public void showToast(String message, int length) {
        if (!getActivity().isFinishing()) {
            Toast toast = Toast.makeText(getActivity(), message, length);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 200);

            toast.show();

        }
    }

    public void dismissDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

}
