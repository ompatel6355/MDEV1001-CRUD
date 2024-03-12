package com.example.mdm_crud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addButton;

    MyDatabase myDB;
    ArrayList<String> Movie_id, Movie_name, Movie_actor, Movie_rating;
    customAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        myDB = new MyDatabase(MainActivity.this);
        Movie_id = new ArrayList<>();
        Movie_name = new ArrayList<>();
        Movie_actor = new ArrayList<>();
        Movie_rating = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new customAdapter(MainActivity.this, Movie_id, Movie_name, Movie_actor, Movie_rating);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                Movie_id.add(cursor.getString(0));
                Movie_name.add(cursor.getString(1));
                Movie_actor.add(cursor.getString(2));
                Movie_rating.add(cursor.getString(3));
            }
        }
    }

}
