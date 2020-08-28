package com.example.drashtimuni.seva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SevaActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button supplier_button;
    Button consumer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seva);
        databaseHelper = new DatabaseHelper(this);

        supplier_button = findViewById(R.id.food_suppier_button);

        supplier_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SevaActivity.this, SupplierActivity.class);
                startActivity(intent);
            }
        });

        consumer_button = findViewById(R.id.food_consumer_button);

        consumer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SevaActivity.this, ConsumerActivity.class);
                startActivity(intent);
            }
        });
    }
}
