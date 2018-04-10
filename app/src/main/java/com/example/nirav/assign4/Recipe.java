package com.example.nirav.assign4;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nirav Jadeja
 */
public class Recipe implements Serializable {
    public  String name;
    public  String ingredients;
    public  String steps;
    public  String foot_notes;
    public  String nutrition_facts;
    public  String ratings;
    public  String link;

    public Recipe() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Recipe(String name, String ingredients, String steps, String foot_notes, String nutrition_facts, String ratings, String link){

        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.foot_notes = foot_notes;
        this.nutrition_facts = nutrition_facts;
        this.ratings = ratings;
        this.link = link;
    }


    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("ingredients", ingredients);
        result.put("steps", steps);
        result.put("foot_notes", foot_notes);
        result.put("nutrition_facts", nutrition_facts);
        result.put("ratings", ratings);
        result.put("link", link);
        return result;
    }
}
