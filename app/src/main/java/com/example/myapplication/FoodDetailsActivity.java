package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailsActivity extends AppCompatActivity {

    private EditText editFoodName, editFoodDetails, editCost, editEstimatedTime;
    private Button btnAddFoodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        editFoodName = findViewById(R.id.editFoodName);
        editFoodDetails = findViewById(R.id.editFoodDetails);
        editCost = findViewById(R.id.editCost);
        editEstimatedTime = findViewById(R.id.editEstimatedTime);
        btnAddFoodItem = findViewById(R.id.btnAddFoodItem);

        btnAddFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodName = editFoodName.getText().toString();
                String foodDetails = editFoodDetails.getText().toString();
                double cost = Double.parseDouble(editCost.getText().toString());
                int estimatedTime = Integer.parseInt(editEstimatedTime.getText().toString());

                FoodItem foodItem = new FoodItem(foodName, foodDetails, cost, estimatedTime);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("foodItem", foodItem);
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });
    }
}