package com.shariarunix.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setBackground(null);

        bottomNav.getMenu().findItem(R.id._dummy).setCheckable(false);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();

    }
}