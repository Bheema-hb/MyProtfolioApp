package com.udacity.nanodegree.myappportfolio.showcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import com.udacity.nanodegree.myappportfolio.R;

/**
 * Created by Bheema on 28/01/16.
 * Company Techjini
 */
public class BaseActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }


    public void showToast(String message, int length) {
        if (!isFinishing()) {
            Toast toast = Toast.makeText(this, message, length);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 200);
            toast.show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
