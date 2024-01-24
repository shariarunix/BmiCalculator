package com.shariarunix.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.shariarunix.bmicalculator.Fragment.ActivityFragment;
import com.shariarunix.bmicalculator.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ImageView imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnBack = findViewById(R.id.img_btn_back);
        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setBackground(null);

        bottomNav.getMenu().findItem(R.id._dummy).setCheckable(false);

        loadFrag(new HomeFragment(), 0);
        bottomNav.getMenu().findItem(R.id._home).setChecked(true);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id._home) {
                    loadFrag(new HomeFragment(), 1);
                    bottomNav.getMenu().findItem(R.id._home).setChecked(true);
                } else if (item.getItemId() == R.id._activity) {
                    loadFrag(new ActivityFragment(), 1);
                    bottomNav.getMenu().findItem(R.id._activity).setChecked(true);
                }

                return false;
            }
        });

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void loadFrag(Fragment fragment, int flag) {

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();

        if (flag == 0) {
            fragTrans.add(R.id.fragment_container, fragment);
        } else {
            fragTrans.replace(R.id.fragment_container, fragment);
        }
        fragTrans.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment currentFragment = manager.findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof HomeFragment) {
            super.onBackPressed();
        } else {
            bottomNav.getMenu().findItem(R.id._home).setChecked(true);
            // Load Home Fragment
            loadFrag(new HomeFragment(), 1);
        }
    }
}