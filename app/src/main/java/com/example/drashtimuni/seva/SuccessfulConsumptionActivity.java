package com.example.drashtimuni.seva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SuccessfulConsumptionActivity extends AppCompatActivity {
    Button button;
    TextView successfullyConsumedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_consumption);
        successfullyConsumedItem = findViewById(R.id.successfully_consumed_item);
        Intent intent = getIntent();
        successfullyConsumedItem.setText(intent.getStringExtra("itemName"));
        button = findViewById(R.id.home_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePageActivity();
            }
        });
    }

    public void openHomePageActivity() {
        Intent intent = new Intent(this, SevaActivity.class);
        startActivity(intent);
    }
}
