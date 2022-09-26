package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;

public class AddNewMovieActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText mid, mov, act, acc, yor, dir;
    private Button addMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        initialize();

        databaseHelper = new DatabaseHelper(this);

        addMovie.setOnClickListener(v -> {
            addArtistToDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void addArtistToDB() {
        String id = mid.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            mid.setError("Enter Movie id");
            mid.requestFocus();
            return;
        }



        boolean result = databaseHelper.insertNewMovie(
                id,
                mov.getText().toString().trim(),
                act.getText().toString().trim(),
                acc.getText().toString().trim(),
                yor.getText().toString().trim(),
                dir.getText().toString().trim()
        );

        if (result){
            startActivity(new Intent(AddNewMovieActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to add movie", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        mid = findViewById(R.id.movIdTxt);
        mov = findViewById(R.id.movNameTxt);
        act = findViewById(R.id.actTxt);
        acc = findViewById(R.id.accTxt);
        yor = findViewById(R.id.yorTxt);
        dir = findViewById(R.id.dirTxt);
        addMovie = findViewById(R.id.addArtistBtn);
    }
}