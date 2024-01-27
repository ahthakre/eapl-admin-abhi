package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private ArrayList<FoodItem> foodItemList;

    public FoodAdapter(ArrayList<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        if (position >= 0 && position < foodItemList.size()) {
            FoodItem foodItem = foodItemList.get(position);
            if (foodItem != null) {
                holder.txtFoodName.setText(foodItem.getFoodName());
                holder.txtFoodDetails.setText(foodItem.getFoodDetails());
                holder.txtCost.setText(String.valueOf(foodItem.getCost()));
                holder.txtEstimatedTime.setText(String.valueOf(foodItem.getEstimatedTime()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return foodItemList != null ? foodItemList.size() : 0;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView txtFoodName, txtFoodDetails, txtCost, txtEstimatedTime;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            txtFoodDetails = itemView.findViewById(R.id.txtFoodDetails);
            txtCost = itemView.findViewById(R.id.txtCost);
            txtEstimatedTime = itemView.findViewById(R.id.txtEstimatedTime);
        }
    }
}