package com.example.mdm_crud;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



public class UpdateActivity extends AppCompatActivity {

    EditText title_input, actor_input, rating_input;
    Button update_button, delete_button;
    String id, title, actor, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        actor_input = findViewById(R.id.actor_input2);
        rating_input = findViewById(R.id.rating_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }


        update_button.setOnClickListener(v -> {
            MyDatabase myDB = new MyDatabase(UpdateActivity.this);
            title = title_input.getText().toString().trim();
            actor = actor_input.getText().toString().trim();
            rating = rating_input.getText().toString().trim();
            myDB.updateData(id, title, actor, rating); // Using 'id' to identify which row to update
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }


    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("actor") && getIntent().hasExtra("rating")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            actor = getIntent().getStringExtra("actor");
            rating = getIntent().getStringExtra("rating");

            title_input.setText(title);
            actor_input.setText(actor);
            rating_input.setText(rating);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title);
        builder.setMessage("Are you sure you want to delete" + title + "?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);

                myDB.deleteData(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.create().show();
    }
}
