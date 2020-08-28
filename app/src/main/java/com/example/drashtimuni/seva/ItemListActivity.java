package com.example.drashtimuni.seva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemListActivity extends AppCompatActivity {
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        next = findViewById(R.id.item_list_next_button);
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openItemConsumptionActivity();
                    }
                }
        );
    }

    public void openItemConsumptionActivity() {
        Intent intent = new Intent(this, ItemConsumptionActivity.class);
        startActivity(intent);
    }
}
