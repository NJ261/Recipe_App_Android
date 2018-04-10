package com.example.nirav.assign4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class create_recipe extends Activity {

    //private FirebaseData appState;
    Recipe receivedPersonInfo;

    private EditText recipeTitle, ingredients, steps, foot_notes, nutrition_facts, link;

    private Spinner ratings;

    FirebaseDatabase database;
    DatabaseReference emailRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(2).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //receivedPersonInfo = (Recipe) getIntent().getSerializableExtra("Contact");
        //appState = ((FirebaseData) getApplicationContext());

        database = FirebaseDatabase.getInstance();
        emailRef = database.getReference("Recipe");

        recipeTitle = (EditText) findViewById(R.id.recipeTitle);
        ingredients = (EditText) findViewById(R.id.ingredientsDescription);
        steps = (EditText) findViewById(R.id.directionsDescription);
        foot_notes = (EditText) findViewById(R.id.foot_notes_description);
        nutrition_facts = (EditText) findViewById(R.id.neutrition_fact_description);
        link = (EditText) findViewById(R.id.url_description);

        ratings = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"*", "* *", "* * *", "* * * *", "* * * * * (5 Stars)" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ratings.setAdapter(adapter);
        ratings.setSelection(0);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    android.content.Intent in;
                    in = new android.content.Intent(getBaseContext(), MainActivity.class);
                    startActivity(in);
                    break;
                case R.id.navigation_dashboard:
                    android.content.Intent in2;
                    in2 = new android.content.Intent(getBaseContext(), search_recipe.class);
                    startActivity(in2);
                    break;
            }
            return true;
        }
    };

    public void createRecipeButton(View v) {

        String name = recipeTitle.getText().toString();
        String recipe_ingredients = ingredients.getText().toString();
        String recipe_steps = steps.getText().toString();
        String recipe_foot_notes = foot_notes.getText().toString();
        String recipe_nutrition_facts = nutrition_facts.getText().toString();
        String recipe_link = link.getText().toString();
        String recipe_ratings = ratings.getSelectedItem().toString();

        int hash = Objects.hash(name);

        if(name.length() > 0 && recipe_ingredients.length() > 0 && recipe_steps.length() > 0){
            Recipe recipe = new Recipe(name, recipe_ingredients, recipe_steps, recipe_foot_notes,recipe_nutrition_facts,recipe_ratings,recipe_link);

            emailRef.child(Integer.toString(hash)).setValue(recipe);
            Toast.makeText(this, "Congratulations! New Recipe has been saved!", Toast.LENGTH_LONG).show();

            android.content.Intent in3;
            in3 = new android.content.Intent(getBaseContext(), MainActivity.class);
            startActivity(in3);
        }
        else{
            Toast.makeText(this, "Please fill Recipe Name, Ingredients and Steps Section and Try Again!.", Toast.LENGTH_LONG).show();
        }
    }
}
