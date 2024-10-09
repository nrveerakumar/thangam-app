package com.thangam.onlineshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.thangam.onlineshopping.myclass.LoadData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.thangam.onlineshopping.fragment.AccountInformationFragment;
import com.thangam.onlineshopping.fragment.CartFragment;
import com.thangam.onlineshopping.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarText;
    View fragment;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbarText);
        fragment = findViewById(R.id.fragment);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", 0);

        // Load initial data into the database
        loadInitialDataIfNeeded();

        View headerView = navigationView.getHeaderView(0);
        TextView profileName = headerView.findViewById(R.id.profileName);
        TextView profileEmail = headerView.findViewById(R.id.profileEmail);

        profileName.setText(sharedPreferences.getString("name", "").toString());
        profileEmail.setText(sharedPreferences.getString("email", "").toString());

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new HomeFragment()).commit();
        toolbarText.setText("Home");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.homeMenu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();
                    toolbarText.setText("Home");
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.accountInformation) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new AccountInformationFragment()).commit();
                    toolbarText.setText("Account Information");
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.cartMenu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new CartFragment()).commit();
                    toolbarText.setText("Cart");
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.feedBackMenu) {
                    Intent i = new Intent(MainActivity.this, FeedBack.class);
                    startActivity(i);
                }
                else if (id == R.id.signOutMenu) {
                    signOut();
                }


                return true;
            }
        });
    }

    private void loadInitialDataIfNeeded() {
        HomeDatabase db = new HomeDatabase(this);
        // Check if data already exists in the database
        if (db.getAllDataUser().isEmpty()) {
            // Only load data if the database is empty
            new LoadData(this);
        }
    }

    public void signOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean("isLogin", false);
        sharedPreferencesEditor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
