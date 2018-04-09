package com.example.nirav.assign4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class show_details extends Activity {

    private TextView recipe_name, recipe_ingridients, recipe_steps, recipe_foot_notes, recipe_nutrition_facts, recipe_ratings, recipe_link;

    Recipe receivedPersonInfo;
    FirebaseDatabase database;
    DatabaseReference emailRef;
    private FirebaseListAdapter<Recipe> firebaseAdapter;

    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        receivedPersonInfo = (Recipe) getIntent().getSerializableExtra("Recipe");
        database = FirebaseDatabase.getInstance();
        emailRef = database.getReference("Recipe");

        recipe_name = (TextView) findViewById(R.id.recipeName_describe);
        recipe_ingridients = (TextView) findViewById(R.id.ingredientsDescribe);
        recipe_steps = (TextView) findViewById(R.id.directions_description);
        recipe_foot_notes = (TextView) findViewById(R.id.foot_notes_describe);
        recipe_nutrition_facts = (TextView) findViewById(R.id.neutrition_fact_describe);
        recipe_ratings = (TextView) findViewById(R.id.ratings_describe);
        recipe_link = (TextView) findViewById(R.id.url_describe);
        editBtn = (Button) findViewById(R.id.editBtn);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(receivedPersonInfo != null) {
            recipe_name.setText(receivedPersonInfo.name); // getting user's saved data - name
            recipe_ingridients.setText(receivedPersonInfo.ingredients); // getting user's saved data - email
            recipe_steps.setText(receivedPersonInfo.steps); // getting user's saved data - number
            recipe_foot_notes.setText(receivedPersonInfo.foot_notes); // getting user's saved data - address
            recipe_nutrition_facts.setText(receivedPersonInfo.nutrition_facts); // getting user's saved data - number
            recipe_ratings.setText(receivedPersonInfo.ratings); // getting user's saved data - address
            recipe_link.setText(receivedPersonInfo.link); // getting user's saved data - address
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(show_details.this, edit_activity.class);
                intent.putExtra("Recipe", receivedPersonInfo);
                startActivity(intent);
            }
        });
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
                case R.id.navigation_notifications:
                    android.content.Intent in3;
                    in3 = new android.content.Intent(getBaseContext(), create_recipe.class);
                    startActivity(in3);
                    break;
            }
            return false;
        }
    };

}

