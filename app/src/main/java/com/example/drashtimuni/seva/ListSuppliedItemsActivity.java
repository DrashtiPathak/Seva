package com.example.drashtimuni.seva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSuppliedItemsActivity extends AppCompatActivity {

    private static final String TAG = "ListSuppliedItemsActivity";
    private static final String NAME = "NAME";

    DatabaseHelper databaseHelper;
    private ExpandableListView listView;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_supplied_items);
        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        populateListView();
        homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSevaActivity();
            }
        });
    }

    private void openSevaActivity() {
        Intent intent = new Intent(this,SevaActivity.class);
        startActivity(intent);
    }

    private void populateListView() {
        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        Cursor cursor = databaseHelper.getAllData();
        while(cursor.moveToNext()) {
            String itemName = cursor.getString(1);
            Map<String, String> curGroupMap = new HashMap<String, String>();
            groupData.add(curGroupMap);
            curGroupMap.put(NAME, itemName);

            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            Map idMap = new HashMap<String, String>();
            idMap.put(NAME,"ID : " + cursor.getInt(0));
            children.add(idMap);
            Map itemNameMap = new HashMap<String, String>();
            itemNameMap.put(NAME,"Item Name : " + itemName);
            children.add(itemNameMap);
            Map typeOfFoodMap = new HashMap<String, String>();
            typeOfFoodMap.put(NAME,"Type of Food : " + cursor.getString(2));
            children.add(typeOfFoodMap);
            Map quantityMap = new HashMap<String, String>();
            quantityMap.put(NAME,"Quantity : " + cursor.getString(3));
            children.add(quantityMap);
            Map expiryMap = new HashMap<String, String>();
            expiryMap.put(NAME,"Expiry Date : " + cursor.getString(4));
            children.add(expiryMap);
            Map perishableMap = new HashMap<String, String>();
            perishableMap.put(NAME,"Perishable food? " + cursor.getString(5));
            children.add(perishableMap);
            Map allergyMap = new HashMap<String, String>();
            allergyMap.put(NAME,"Allergy Info : " + cursor.getString(6));
            children.add(allergyMap);
            Map supplierMap = new HashMap<String, String>();
            supplierMap.put(NAME,"Supplier Name : " + cursor.getString(7));
            children.add(supplierMap);
            Map addressMap = new HashMap<String, String>();
            addressMap.put(NAME,"Address : " + cursor.getString(8));
            children.add(addressMap);
            Map pickUpTimeMap = new HashMap<String, String>();
            pickUpTimeMap.put(NAME,"Pick up time : " + cursor.getString(9));
            children.add(pickUpTimeMap);
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