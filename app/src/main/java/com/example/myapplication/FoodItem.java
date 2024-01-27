package com.example.myapplication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FoodItem implements Serializable {

    private String foodId;
    private String foodName;
    private String foodDetails;
    private double cost;
    private int estimatedTime;

    public FoodItem() {
    }

    public FoodItem(String foodName, String foodDetails, double cost, int estimatedTime) {
        this.foodId = UUID.randomUUID().toString();
        this.foodName = foodName;
        this.foodDetails = foodDetails;
        this.cost = cost;
        this.estimatedTime = estimatedTime;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDetails() {
        return foodDetails;
    }

    public double getCost() {
        return cost;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("foodName", foodName);
        result.put("foodDetails", foodDetails);
        result.put("cost", cost);
        result.put("estimatedTime", estimatedTime);
        return result;
    }
}