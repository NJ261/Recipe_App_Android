package com.example.nirav.assign4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
