package com.example.tdma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Retrieve shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");  // Default to empty if not found

        // Find the TextView in the layout
        TextView textView8 = findViewById(R.id.textView8);

        // Set the welcome message with the retrieved username
        String welcomeMessage = "Welcome " + username;
        textView8.setText(welcomeMessage);
        Toast.makeText(getApplicationContext(), welcomeMessage, Toast.LENGTH_SHORT).show();

        // Handle logout button click
        CardView exit = findViewById(R.id.logout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all saved user details
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Navigate to LoginActivity
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                // Clear the back stack and start a new task
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();  // Finish the current activity
            }
        });

        // Handle hotels card click
        CardView hotels = findViewById(R.id.hotels);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AccomodationActivity.class));
            }
        });

        // Handle parks card click
        CardView parks = findViewById(R.id.parks);
        parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NationalActivity.class));
            }
        });

        CardView mountains = findViewById(R.id.mountains);
        mountains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MountainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Override the back button press to log out and navigate to LoginActivity
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Navigate to LoginActivity and clear all activities
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();  // Finish the current activity
    }
}
