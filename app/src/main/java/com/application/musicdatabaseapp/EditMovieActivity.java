package com.application.musicdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.musicdatabaseapp.db.DatabaseHelper;
import com.application.musicdatabaseapp.models.ArtistModel;
import com.google.gson.Gson;

public class EditMovieActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText mid, movName, act, acc, yor, dir;
    private Button editMov, deleteMov;
    private ArtistModel artistModel = null;
    public static final String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artist);

        initialize();

        if (getIntent() != null){
            if (getIntent().hasExtra("artist")){
                String data = getIntent().getStringExtra("artist");
                Gson gson = new Gson();
                artistModel = gson.fromJson(data, ArtistModel.class);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        if (artistModel != null){
            mid.setText(artistModel.getMov_id());
            movName.setText(artistModel.getMovName());
            act.setText(artistModel.getAct());
            acc.setText(artistModel.getAcc());
            yor.setText(artistModel.getYor());
            dir.setText(artistModel.getDir());
        }

        editMov.setOnClickListener(v -> {
            editArtistToDB();
        });

        deleteMov.setOnClickListener(v -> {
            deleteMovFromDB();
        });

        findViewById(R.id.backBtn).setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void initialize() {
        mid = findViewById(R.id.movIdTxt);
        movName = findViewById(R.id.movNameTxt);
        act = findViewById(R.id.actTxt);
        acc = findViewById(R.id.accTxt);
        yor = findViewById(R.id.yorTxt);
        dir = findViewById(R.id.dirTxt);
        editMov = findViewById(R.id.editMovBtn);
        deleteMov = findViewById(R.id.deleteMovBtn);
    }

    private void deleteMovFromDB() {
        String id = mid.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            mid.setError("Enter Movie id");
            mid.requestFocus();
            return;
        }

        databaseHelper.deleteMovie(id);

        startActivity(new Intent(EditMovieActivity.this, MainActivity.class));
        finish();
    }

    private void editArtistToDB() {
        String id = mid.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            mid.setError("Enter Movie id");
            mid.requestFocus();
            return;
        }


        int result = databaseHelper.updateMovie(
                id,
                movName.getText().toString().trim(),
                act.getText().toString().trim(),
                acc.getText().toString().trim(),
                yor.getText().toString().trim(),
                dir.getText().toString().trim()
        );

        if (result == 1){
            startActivity(new Intent(EditMovieActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Failed to update movie", Toast.LENGTH_SHORT).show();
        }
    }
}