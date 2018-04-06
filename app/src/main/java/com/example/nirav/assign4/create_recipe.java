package com.example.nirav.assign4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class create_recipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
    }

    private android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@android.support.annotation.NonNull android.view.MenuItem item) {
            android.content.Intent in;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    in = new android.content.Intent(getBaseContext(), MainActivity.class);
                    startActivity(in);
                    return true;
                case R.id.navigation_dashboard:
                    in = new android.content.Intent(getBaseContext(), search_recipe.class);
                    startActivity(in);
                    return true;
                case R.id.navigation_notifications:
                    in = new android.content.Intent(getBaseContext(), create_recipe.class);
                    startActivity(in);
                    return true;
            }
            return false;
        }
    };

}
