/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.networkconnect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.networkconnect.model.CharacterProfile;
import com.example.android.networkconnect.model.CharacterResponse;
import com.example.android.networkconnect.ui.CharacterAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

/**
 * Sample Activity demonstrating how to connect to the network and fetch raw
 * HTML. It uses a Fragment that encapsulates the network operations on an AsyncTask.
 *
 * This sample uses a TextView to display output.
 */
public class MainActivity extends FragmentActivity implements DownloadCallback {

    private static final String API = "https://rickandmortyapi.com/api/character";

    //Reference to the welcoming message TextView
    private TextView introText;

    // Reference to the recycler view showing fetched data, so we can clear it with a button
    // as necessary.
    private RecyclerView characterList;

    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;

    //Reference to the ProgressBar, to know when if it is fetching the data
    private ProgressBar mProgressBar;

    //Reference to the adapter
    private  CharacterAdapter mCharacterAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);

        characterList = findViewById(R.id.characterList);
        introText = findViewById(R.id.intro_text);

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);

        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), API);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // When the user clicks FETCH, fetch the first 500 characters of
            // raw HTML from www.google.com.
            case R.id.fetch_action:
                introText.setVisibility(View.GONE);
                characterList.setAdapter(null);
                startDownload();
                return true;
            // Clear the text and cancel download.
            case R.id.clear_action:
                finishDownloading();
                characterList.setAdapter(null);
                introText.setVisibility(View.VISIBLE);
                return true;
        }
        return false;
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {

            //Show the progressBar
            mProgressBar.setVisibility(View.VISIBLE);

            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }

    @Override
    public void updateFromDownload(String result) {
        if (result != null) {

            //Convert the fetch result into an object of type CharacterResponse
            Gson gson = new Gson();
            CharacterResponse apiResponse = gson.fromJson(result,CharacterResponse.class);
            mCharacterAdapter = new CharacterAdapter(apiResponse);
            characterList.setAdapter(mCharacterAdapter);

        } else {

            //Shows a short Toast with a connection error
            Toast.makeText(this,getString(R.string.connection_error),Toast.LENGTH_SHORT)
                    .show();

            mProgressBar.setVisibility(View.GONE);

        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public void finishDownloading() {
        mProgressBar.setVisibility(View.GONE);
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                break;
            case Progress.CONNECT_SUCCESS:
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                break;
        }
    }
}
