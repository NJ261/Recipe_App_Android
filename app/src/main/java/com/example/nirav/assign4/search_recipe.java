package com.example.nirav.assign4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Author: Nirav Jadeja
 *
 * This is a search activity which has a search bar
 * and display the result accroding to the search.
 * A user can also open the description of a recipe from the result.
 */

// https://developer.android.com/guide/topics/search/search-dialog.html#SearchableConfiguration
public class search_recipe extends Activity {

    private ListView searchView;
    FirebaseDatabase database;
    DatabaseReference emailRef;
    private FirebaseListAdapter<Recipe> search_firebaseAdapter;
    private EditText search_box;    //search box
    private String temp;            // temp for string matching

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        database = FirebaseDatabase.getInstance();
        emailRef = database.getReference("Recipe");
        search_box = (EditText) findViewById(R.id.search_box);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Get the reference to the UI contents
        searchView = (ListView) findViewById(R.id.listView1);


        //Set up the List View
        search_firebaseAdapter = new FirebaseListAdapter<Recipe>(this, Recipe.class,
                android.R.layout.simple_list_item_1, emailRef) {
            @Override
            protected void populateView(final View v, final Recipe model, final int position) {

                // text change listener for search bar
                search_box.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        temp = s.toString();

                        // check for match in recipe title and gives the result
                        if (model.name.contains(temp)){
                            TextView recipe_name = (TextView) v.findViewById(android.R.id.text1);
                            recipe_name.setText(model.name);
                            recipe_name.setVisibility(v.VISIBLE);
                        }
                        else {
                            TextView recipe_name = (TextView) v.findViewById(android.R.id.text1);
                            recipe_name.setVisibility(v.GONE);
                        }
                    }
                });

            }
        };
        searchView.setAdapter(search_firebaseAdapter);
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe) search_firebaseAdapter.getItem(position);
                showDetailView(recipe);
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

                case R.id.navigation_notifications:
                    android.content.Intent in3;
                    in3 = new android.content.Intent(getBaseContext(), create_recipe.class);
                    startActivity(in3);
                    break;
            }
            return true;
        }
    };

    // function for opening the description of a recipe
    private void showDetailView(Recipe recipe){
        Intent intent = new Intent(this, show_details.class);
        intent.putExtra("Recipe", recipe);
        startActivity(intent);
    }

}
