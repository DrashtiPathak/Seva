package com.example.drashtimuni.seva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumerListActivity extends AppCompatActivity {

    private static final String TAG = "ConsumerListActivity";
    private static final String NAME = "NAME";

    DatabaseHelper databaseHelper;
    private ExpandableListView listView;
    private Button homeButton;
    private Button orderItemButton;

    private List<Map<String, String>> groupData;
    private List<List<Map<String, String>>> childData;
    List<Map<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.consumer_list_view);
        databaseHelper = new DatabaseHelper(this);
        populateListView();

        homeButton = findViewById(R.id.home2_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSevaActivity();
            }
        });

        orderItemButton = findViewById(R.id.consume_item_button);
        orderItemButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumerListActivity.this, ConsumeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openSevaActivity() {
        Intent intent = new Intent(this,SevaActivity.class);
        startActivity(intent);
    }

    private void populateListView() {
        groupData = new ArrayList<Map<String, String>>();
        childData = new ArrayList<List<Map<String, String>>>();
        dataList = new ArrayList<Map<String, String>>();
        Cursor cursor = databaseHelper.getAllData();
        while(cursor.moveToNext()) {
            Map<String, String> dataMap = new HashMap<String, String>();
            String itemName = cursor.getString(1);
            Map<String, String> curGroupMap = new HashMap<String, String>();
            groupData.add(curGroupMap);
            curGroupMap.put(NAME, itemName);

            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            Map idMap = new HashMap<String, String>();
            int id = cursor.getInt(0);
            idMap.put(NAME,"ID : " + id);
            children.add(idMap);
            dataMap.put("id", ""+id);
            Map itemNameMap = new HashMap<String, String>();
            itemNameMap.put(NAME,"Item Name : " + itemName);
            children.add(itemNameMap);
            dataMap.put("itemName", itemName);
            Map typeOfFoodMap = new HashMap<String, String>();
            String typeOfFood = cursor.getString(2);
            typeOfFoodMap.put(NAME,"Type of Food : " + typeOfFood);
            children.add(typeOfFoodMap);
            dataMap.put("typeOfFood", typeOfFood);
            Map quantityMap = new HashMap<String, String>();
            String quantity = cursor.getString(3);
            quantityMap.put(NAME,"Quantity : " + quantity);
            children.add(quantityMap);
            dataMap.put("quantity", quantity);
            Map expiryMap = new HashMap<String, String>();
            String expiryDate = cursor.getString(4);
            expiryMap.put(NAME,"Expiry Date : " + expiryDate);
            children.add(expiryMap);
            dataMap.put("expiryDate", expiryDate);
            Map perishableMap = new HashMap<String, String>();
            String perishableFood = cursor.getString(5);
            perishableMap.put(NAME,"Perishable food? " + perishableFood);
            children.add(perishableMap);
            dataMap.put("perishableFood", perishableFood);
            Map allergyMap = new HashMap<String, String>();
            String allergy = cursor.getString(6);
            allergyMap.put(NAME,"Allergy Info : " + allergy);
            children.add(allergyMap);
            dataMap.put("allergy", allergy);
            Map supplierMap = new HashMap<String, String>();
            String supplier = cursor.getString(7);
            supplierMap.put(NAME,"Supplier Name : " + supplier);
            children.add(supplierMap);
            dataMap.put("supplier", supplier);
            Map addressMap = new HashMap<String, String>();
            String address = cursor.getString(8);
            addressMap.put(NAME,"Address : " + address);
            children.add(addressMap);
            dataMap.put("address", address);
            Map pickUpTimeMap = new HashMap<String, String>();
            String pickUpTime = cursor.getString(9);
            pickUpTimeMap.put(NAME,"Pick up time : " + pickUpTime);
            children.add(pickUpTimeMap);
            dataMap.put("pickUpTime", pickUpTime);
            childData.add(children);
        }
        ExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] {NAME}, new int[] { android.R.id.text1 },
                childData, android.R.layout.simple_expandable_list_item_2,
                new String[] { NAME }, new int[] { android.R.id.text1 });
        listView.setAdapter(adapter);
    }

}
