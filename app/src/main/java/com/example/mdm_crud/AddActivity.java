package com.example.mdm_crud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText titleInput, actorInput, ratingInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleInput = findViewById(R.id.title_input);
        actorInput = findViewById(R.id.actor_input);
        ratingInput = findViewById(R.id.rating_input);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(view -> {
            MyDatabase myDB;
            myDB = new MyDatabase(AddActivity.this);
            myDB.addMovie(titleInput.getText().toString().trim(),
                    actorInput.getText().toString().trim(),
                    Integer.parseInt(ratingInput.getText().toString().trim()));
        });
    }
}
