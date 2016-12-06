package com.udacity.nanodegree.myappportfolio.showcase.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.nanodegree.myappportfolio.R;

/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class BaseActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;


    public void showToast(String message, int length) {
        if (!isFinishing()) {
            Toast toast = Toast.makeText(this, message, length);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 200);
            toast.show();
        }
    }



    public synchronized void showLoadingDialog(int resId) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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


    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }


    protected void setBackEnabled(boolean flag) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(flag);
            getSupportActionBar().setHomeButtonEnabled(flag);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getSupportFragmentManager().getFragments() != null) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }
}
