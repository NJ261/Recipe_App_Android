package com.example.nirav.assign4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class edit_activity extends Activity {

    private EditText edit_name, edit_ingridients, edit_steps, edit_foot_notes, edit_nutrition_facts, edit_ratings, edit_link;

    Recipe receivedPersonInfo;
    FirebaseDatabase database;
    DatabaseReference emailRef;
    private FirebaseListAdapter<Recipe> firebaseAdapter;
    private Button saveBtn, deleteBtn;
    private Spinner ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);

        receivedPersonInfo = (Recipe) getIntent().getSerializableExtra("Recipe");
        database = FirebaseDatabase.getInstance();
        emailRef = database.getReference("Recipe");

        edit_name = (EditText) findViewById(R.id.edit_title);
        edit_ingridients = (EditText) findViewById(R.id.edit_ingredients);
        edit_steps = (EditText) findViewById(R.id.edit_steps);
        edit_foot_notes = (EditText) findViewById(R.id.edit_foot_notes);
        edit_nutrition_facts = (EditText) findViewById(R.id.edit_neutrition_fact);
        edit_link = (EditText) findViewById(R.id.edit_url);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);

        ratings = (Spinner) findViewById(R.id.edit_spinner1);
        String[] items = new String[]{"*", "* *", "* * *", "* * * *", "* * * * * (5 Stars)" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ratings.setAdapter(adapter);
        ratings.setSelection(0);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(receivedPersonInfo != null) {
            edit_name.setText(receivedPersonInfo.name); // getting user's saved data - name
            edit_ingridients.setText(receivedPersonInfo.ingredients); // getting user's saved data - ingredients
            edit_steps.setText(receivedPersonInfo.steps); // getting user's saved data - steps to follow
            edit_foot_notes.setText(receivedPersonInfo.foot_notes); // getting user's saved data - foot notes
            edit_nutrition_facts.setText(receivedPersonInfo.nutrition_facts); // getting user's saved data - nutrition facts
            edit_link.setText(receivedPersonInfo.link); // getting user's saved data - web or video link

            int ratings_value = adapter.getPosition(receivedPersonInfo.ratings); // getting user's saved data - ratings
            ratings.setSelection(ratings_value);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edit_name.getText().toString();
                String ingredients = edit_ingridients.getText().toString();
                String steps = edit_steps.getText().toString();
                String foot_notes = edit_foot_notes.getText().toString();
                String nutrition_facts = edit_nutrition_facts.getText().toString();
                String link = edit_link.getText().toString();

                String edit_ratings = ratings.getSelectedItem().toString();

                int hash = Objects.hash(name);

                if(name.length() > 0 && ingredients.length() > 0 && steps.length() > 0){

                    String totalData[] = {name, ingredients, steps, foot_notes, nutrition_facts, edit_ratings, link};
                    String totalDataField[] = {"name", "ingredients", "steps", "foot_notes", "nutrition_facts", "ratings", "link"};

                    for(int j=0; j<7; j++) {
                        emailRef.child(Integer.toString(hash)).child(totalDataField[j]).setValue(totalData[j]);
                    }
                    Toast.makeText(edit_activity.this, "Changes Saved!!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(edit_activity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(edit_activity.this, "Please fill Recipe Name, Ingredients and Steps Section and Try Again!.", Toast.LENGTH_LONG).show();
                }


            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edit_name.getText().toString();
                int hash = Objects.hash(name);
                emailRef.child(Integer.toString(hash)).removeValue();
                Toast.makeText(edit_activity.this, "Recipe Deleted!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(edit_activity.this, MainActivity.class);
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
            return true;
        }
    };
}
