package com.example.nirav.assign4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class create_recipe extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
            return false;
        }
    };

}
