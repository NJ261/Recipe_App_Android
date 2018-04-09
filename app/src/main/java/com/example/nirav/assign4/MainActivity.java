package com.example.nirav.assign4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    private TextView mTextMessage;
    private ListView contactListView;
    FirebaseDatabase database;
    DatabaseReference emailRef;
    private FirebaseListAdapter<Recipe> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        database = FirebaseDatabase.getInstance();
        emailRef = database.getReference("Recipe");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Recipe>(this, Recipe.class,
                android.R.layout.simple_list_item_1, emailRef) {
            @Override
            protected void populateView(View v, Recipe model, int position) {
                TextView recipe_name = (TextView)v.findViewById(android.R.id.text1);
                recipe_name.setText(model.name);

            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe) firebaseAdapter.getItem(position);
                showDetailView(recipe);
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
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

    private void showDetailView(Recipe recipe){
        Intent intent = new Intent(this, show_details.class);
        intent.putExtra("Recipe", recipe);
        startActivity(intent);
    }
}
