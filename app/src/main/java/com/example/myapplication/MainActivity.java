package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private ArrayList<FoodItem> foodItemList;

    private DatabaseReference databaseReference;

    private static final int REQUEST_CODE_ADD_FOOD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        foodItemList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);

        // Set the main "Admin" child reference in Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin").child("foodItems");
        
        fetchDataFromFirebase();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, FoodDetailsActivity.class), REQUEST_CODE_ADD_FOOD);
            }
        });
    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = snapshot.getValue(FoodItem.class);
                    if (foodItem != null) {
                        foodItemList.add(foodItem);
                    }
                }
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_FOOD && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("foodItem")) {
                FoodItem foodItem = (FoodItem) data.getSerializableExtra("foodItem");

                String foodItemId=databaseReference.push().getKey();
                Log.d("Firebase","Generated UUID for new food item:"+foodItemId);
                if(foodItemId!=null){
                    databaseReference.child(foodItem.getFoodId()).setValue(foodItem.toMap());
                }

                // Save the food item to Firebase within the "Admin" child with generated UUID

                // Update the local list and notify the adapter
                foodItemList.add(foodItem);
                foodAdapter.notifyDataSetChanged();
            }
        }
    }
}