package com.example.nirav.assign4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;


// https://developer.android.com/guide/topics/search/search-dialog.html#SearchableConfiguration
public class search_recipe extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setChecked(true);
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
