package com.example.nirav.assign4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Author: Nirav Jadeja
 *
 * This activity shows the details of a recipe
 */
public class show_details extends Activity {

    private TextView recipe_name, recipe_ingridients, recipe_steps, recipe_foot_notes, recipe_nutrition_facts, recipe_ratings, recipe_link;

    Recipe receivedPersonInfo; // accessing recipe details via recipe class
    FirebaseDatabase database;
    DatabaseReference emailRef;
    private FirebaseListAdapter<Recipe> firebaseAdapter;

    private Button editBtn;     // edit button

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

        // displaying values
        if(receivedPersonInfo != null) {
            recipe_name.setText(receivedPersonInfo.name); // getting user's saved data - name
            recipe_ingridients.setText(receivedPersonInfo.ingredients); // getting user's saved data - ingredients
            recipe_steps.setText(receivedPersonInfo.steps); // getting user's saved data - steps to follow
            recipe_foot_notes.setText(receivedPersonInfo.foot_notes); // getting user's saved data - foot notes
            recipe_nutrition_facts.setText(receivedPersonInfo.nutrition_facts); // getting user's saved data - nutrition facts
            recipe_ratings.setText(receivedPersonInfo.ratings); // getting user's saved data - ratings
            recipe_link.setText(receivedPersonInfo.link); // getting user's saved data - web or video link

            recipe_link.setMovementMethod(LinkMovementMethod.getInstance());
            recipe_link.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(receivedPersonInfo.link));
                    startActivity(browserIntent);
                }
            });
        }

        // edit button which leads to update form for the recipe
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(show_details.this, edit_activity.class);
                intent.putExtra("Recipe", receivedPersonInfo);
                startActivity(intent);
            }
        });
    }

    // bottom navigation
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
            return true;
        }
    };
}

