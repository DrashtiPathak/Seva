package com.example.drashtimuni.seva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SupplierActivity extends AppCompatActivity {

    Button addItemButton;
    Button viewItemsButton;
    EditText itemName;
    EditText typeOfFood;
    EditText quantity;
    EditText dateOfExpiry;
    EditText perishable;
    EditText allergies;
    EditText supplierName;
    EditText address;
    EditText pickupTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplier);
        addItemButton = findViewById(R.id.add_item_button);
        viewItemsButton = findViewById(R.id.view_items_button);
        itemName = findViewById(R.id.item_name_edit_text);
        typeOfFood = findViewById(R.id.hot_or_cold_edit_text);
        quantity = findViewById(R.id.quantity_edit_text);
        dateOfExpiry = findViewById(R.id.date_of_expiry_edit_text);
        perishable = findViewById(R.id.perishable_edit_text);
        allergies = findViewById(R.id.allergies_edit_text);
        supplierName = findViewById(R.id.supplier_name_edit_text);
        address = findViewById(R.id.address_edit_text);
        pickupTime = findViewById(R.id.pickup_time_edit_text);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemName.length() != 0) {
                    if(insertItem()) {
                        toastMessage("Item successfully added!");
                    } else {
                        toastMessage("Item couldn't added. Please try again later.");
                    }
                } else {
                    toastMessage("Please put Item Name");
                }
            }
        });

        viewItemsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openListSuppliedItemsActivity();
            }
        });
    }

    private void openListSuppliedItemsActivity() {
        Intent intent = new Intent(this,ListSuppliedItemsActivity.class);
        startActivity(intent);
    }

    public boolean insertItem() {
        return new DatabaseHelper(this).insertItem(itemName.getText().toString(), typeOfFood.getText().toString(), quantity.getText().toString(),
                dateOfExpiry.getText().toString(), perishable.getText().toString(), allergies.getText().toString(), supplierName.getText().toString(),
                address.getText().toString(), pickupTime.getText().toString());
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
