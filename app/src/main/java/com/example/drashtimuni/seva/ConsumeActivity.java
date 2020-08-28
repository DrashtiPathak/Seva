package com.example.drashtimuni.seva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumeActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.consume_id_edit_text);
        button = findViewById(R.id.consume_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String id = editText.getText().toString();
                List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
                Cursor cursor = new DatabaseHelper(ConsumeActivity.this).getAllDataForId(id);
                Intent intent = new Intent(ConsumeActivity.this, SuccessfulConsumptionActivity.class);
                while(cursor.moveToNext()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    dataMap.put("id", ""+cursor.getInt(0));
                    dataMap.put("itemName", cursor.getString(1));
                    dataMap.put("typeOfFood", cursor.getString(2));
                    dataMap.put("quantity", cursor.getString(3));
                    dataMap.put("expiryDate", cursor.getString(4));
                    dataMap.put("perishableFood", cursor.getString(5));
                    dataMap.put("allergy", cursor.getString(6));
                    dataMap.put("supplier", cursor.getString(7));
                    dataMap.put("address", cursor.getString(8));
                    dataMap.put("pickUpTime", cursor.getString(9));
                    dataList.add(dataMap);
                }

                if(dataList.size() < 1) {
                    Toast.makeText(ConsumeActivity.this, "No item consumed. Please try again with correct id", Toast.LENGTH_SHORT).show();
                } else {
                    new DatabaseHelper(ConsumeActivity.this).deleteDataForId(id);
                    for(String key : dataList.get(0).keySet()) {
                        intent.putExtra(key, dataList.get(0).get(key));
                    }
                    startActivity(intent);
                }
            }
        });
    }
}