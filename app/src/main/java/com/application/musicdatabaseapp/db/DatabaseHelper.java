package com.application.musicdatabaseapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.application.musicdatabaseapp.models.ArtistModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "music_db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("PRAGMA foreign_keys=ON");

        String moviesTable = "create table movies (" +
                "Movie_id varchar(5) primary key, " +
                "Movie_Name varchar(15), " +
                "Lead_Actor varchar(15), " +
                "Actress varchar(15), " +
                "Year_Of_Release varchar(15), " +
                "Director_Name varchar(15));";

        try {
            DB.execSQL(moviesTable);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("PRAGMA foreign_keys=ON");

        DB.execSQL("DROP TABLE IF EXISTS movies");

        onCreate(DB);
    }


    public boolean insertNewMovie(String Movie_id,
                                String Movie_Name,
                                  String Lead_Actor,
                                  String Actress,
                                  String Year_Of_Release,
                                  String Director_Name
                                  ) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Movie_id", Movie_id);
            contentValues.put("Movie_Name", Movie_Name);
            contentValues.put("Lead_Actor", Lead_Actor);
            contentValues.put("Actress", Actress);
            contentValues.put("Year_Of_Release", Year_Of_Release);
            contentValues.put("Director_Name", Director_Name);
            DB.replace("movies", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    @SuppressLint("Range")
    public ArrayList<ArtistModel> getAllMovies() {

        ArrayList<ArtistModel> artistModelArrayList = new ArrayList<>();

        String selectQuery = "select * from movies";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                ArtistModel artistModel = new ArtistModel();
                artistModel.setMov_id(c.getString(c.getColumnIndex("Movie_id")));
                artistModel.setMovName(c.getString(c.getColumnIndex("Movie_Name")));
                artistModel.setAct(c.getString(c.getColumnIndex("Lead_Actor")));
                artistModel.setAcc(c.getString(c.getColumnIndex("Actress")));
                artistModel.setYor(c.getString(c.getColumnIndex("Year_Of_Release")));
                artistModel.setDir(c.getString(c.getColumnIndex("Director_Name")));

                artistModelArrayList.add(artistModel);
            }
            while (c.moveToNext());
        }

        return artistModelArrayList;

    }




    public int updateMovie(String Movie_id,
                           String Movie_Name,
                           String Lead_Actor,
                           String Actress,
                           String Year_Of_Release,
                           String Director_Name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Movie_id", Movie_id);
        contentValues.put("Movie_Name", Movie_Name);
        contentValues.put("Lead_Actor", Lead_Actor);
        contentValues.put("Actress", Actress);
        contentValues.put("Year_Of_Release", Year_Of_Release);
        contentValues.put("Director_Name", Director_Name);

        return DB.update("movies", contentValues, "Movie_id = ?", new String[]{String.valueOf(Movie_id)});
    }

    public void deleteMovie(String Movie_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("movies", "Movie_id = ?", new String[]{String.valueOf(Movie_id)});
    }
}
