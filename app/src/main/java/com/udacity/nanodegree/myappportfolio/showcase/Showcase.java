package com.udacity.nanodegree.myappportfolio.showcase;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.udacity.nanodegree.myappportfolio.R;
import com.udacity.nanodegree.myappportfolio.showcase.activities.BaseActivity;


public class Showcase extends BaseActivity implements View.OnClickListener {

    Button mSpotifyButton, mScoreButton, mLibraryButton, mBuildButton, mXyzButton, mCapstoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);
        mScoreButton = (Button) findViewById(R.id.scores_button);
        mSpotifyButton = (Button) findViewById(R.id.spotify_button);
        mLibraryButton = (Button) findViewById(R.id.library_button);
        mBuildButton = (Button) findViewById(R.id.build_bigger_button);
        mXyzButton = (Button) findViewById(R.id.xyz_button);
        mCapstoneButton = (Button) findViewById(R.id.capstone_button);

        mScoreButton.setOnClickListener(this);
        mSpotifyButton.setOnClickListener(this);
        mLibraryButton.setOnClickListener(this);
        mBuildButton.setOnClickListener(this);
        mXyzButton.setOnClickListener(this);
        mCapstoneButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_showcase, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String message = "";

        switch (v.getId()) {
            case R.id.scores_button:
                message = getString(R.string.this_will_launch) + " Score App";
                break;
            case R.id.spotify_button:

                message = getString(R.string.this_will_launch) + " Spotify Streamer App";
                break;
            case R.id.library_button:

                message = getString(R.string.this_will_launch) + " Library App";
                break;
            case R.id.capstone_button:

                message = getString(R.string.this_will_launch) + " Capstone App";
                break;
            case R.id.build_bigger_button:

                message = getString(R.string.this_will_launch) + " Build It Bigger App";
                break;
            case R.id.xyz_button:

                message = getString(R.string.this_will_launch) + " XYZ Reader App";
                break;


        }
        showToast(message, Toast.LENGTH_SHORT);
    }
}
