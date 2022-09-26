package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.application.musicdatabaseapp.adapters.ArtistRVAdapter;
import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.ArtistModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayList<ArtistModel> artistModelArrayList;
    public static final String TAG = "ABCD";

    private TextView noArtistData, noMovieSongData, noAlbumSongData, noUserData, noPlaylistData, noPodcasterData, noPodcastData;
    private RecyclerView artistRV, movieSongRV, albumSongRV, userRV, playlistRV, podcasterRV, podcastRV;
    private Button addArtist, addMovieSongBtn, addAlbumSongBtn, addUserBtn, addPlaylistBtn, addPodcasterBtn, addPodcastBtn;

    private ArtistRVAdapter artistRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        artistModelArrayList = databaseHelper.getAllMovies();

        artistRVAdapter = new ArtistRVAdapter(this, artistModelArrayList);
        artistRV.setAdapter(artistRVAdapter);

        if (artistModelArrayList.size() == 0){
            artistRV.setVisibility(View.GONE);
            noArtistData.setVisibility(View.VISIBLE);
        }
        else{
            noArtistData.setVisibility(View.GONE);
            artistRV.setVisibility(View.VISIBLE);
        }

        addArtist.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNewMovieActivity.class));
        });

    }

    private void initialize() {
        noArtistData = findViewById(R.id.noArtistData);

        artistRV = findViewById(R.id.artistRV);
        artistRV.setHasFixedSize(true);

        addArtist = findViewById(R.id.addArtistBtn);
    }

    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}