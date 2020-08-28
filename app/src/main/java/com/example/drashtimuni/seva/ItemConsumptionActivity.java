package com.example.drashtimuni.seva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemConsumptionActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_consumption);
        button = findViewById(R.id.consume_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuccessfulConsumptionActivity();
            }
        });
    }

    public void openSuccessfulConsumptionActivity() {
        Intent intent = new Intent(this, SuccessfulConsumptionActivity.class);
        startActivity(intent);
    }
}
