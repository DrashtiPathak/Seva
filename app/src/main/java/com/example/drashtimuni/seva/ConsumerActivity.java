package com.example.drashtimuni.seva;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConsumerActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button viewAllFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer);

        viewAllFoodItems = findViewById(R.id.view_food_items_button);
        viewAllFoodItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemListActivity();
            }
        });
    }

    public void openItemListActivity() {
        Intent intent = new Intent(this, ConsumerListActivity.class);
        startActivity(intent);
    }
}
