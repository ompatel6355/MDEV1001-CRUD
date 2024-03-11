package com.example.mdm_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MovieList.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "MyLibrary";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "Movie_title";
    public static final String COLUMN_ACTOR = "Movie_Actor";
    public static final String COLUMN_RATING = "Column_Rating"; // Corrected column name
    private final Context context;


    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_ACTOR + " TEXT, " +
                COLUMN_RATING + " INTEGER);"; // Use corrected column name
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//     void addMovie(String title, String actor, Integer rating) {
//
//    }

    public void addMovie(String title, String actor, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_ACTOR, actor);
        cv.put(COLUMN_RATING, rating); // Use corrected column name

        long result = db.insert("MyLibrary", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}

