package com.example.tdma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HotelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelList = new ArrayList<>();

        // Populate hotelList with six dummy hotels (replace with actual data retrieval)
        hotelList.add(new Hotel("Serena Hotel", "Experience luxury in the heart of downtown, where elegance meets comfort and sophistication. Indulge in exquisite dining, spa treatments, and breathtaking city views.", "Starting at 75,000/tsh per night", Arrays.asList(R.drawable.serena, R.drawable.serena2, R.drawable.serena3)));

        hotelList.add(new Hotel("Paradise Resort", "Discover a secluded paradise nestled along pristine beaches with panoramic ocean views. Enjoy ultimate relaxation and luxury amenities amidst lush tropical surroundings.", "Starting at 100,000/tsh per night", Arrays.asList(R.drawable.villa,R.drawable.serena2, R.drawable.house)));
        hotelList.add(new Hotel("City Suites", "Immerse yourself in modern elegance and convenience in the bustling heart of the city. Perfect for business travelers and urban explorers seeking comfort and style.", "Starting at 120,000/tsh per night", Arrays.asList(R.drawable.suits1, R.drawable.suite2, R.drawable.suit3)));
        hotelList.add(new Hotel("Ocean View Hotel", "Savor breathtaking ocean views from every corner of our luxurious hotel. Unwind with seaside dining, spa treatments, and sunset vistas that create unforgettable memories.", "Starting at 150,000/tsh per night", Arrays.asList(R.drawable.beach1, R.drawable.beach2, R.drawable.beach3)));
        hotelList.add(new Hotel("Mountain Meru Hotel", "Escape to a peaceful sanctuary amidst majestic mountain peaks. Enjoy nature hikes, cozy evenings by the fire, and breathtaking panoramic views of untouched wilderness.", "Starting at 200,000/tsh per night", Arrays.asList(R.drawable.meru3, R.drawable.meru2, R.drawable.meru3)));
        hotelList.add(new Hotel("Lakefront Lodge", "Experience serenity at our charming lodge nestled on the tranquil shores of a crystal-clear lake. Relax with lakeside dining, water sports, and stunning sunsets.", "Starting at 100,00/tsh per night",  Arrays.asList(R.drawable.lake, R.drawable.lake2, R.drawable.lake3)));


        // Initialize HotelAdapter with hotelList
        hotelAdapter = new HotelAdapter(this, hotelList);
        recyclerView.setAdapter(hotelAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    // Handle Home action
                    startActivity(new Intent(HotelActivity.this, HomeActivity.class));
                    return true;

                case R.id.nav_logout:
                    // Handle Logout action
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(HotelActivity.this, LoginActivity.class));
                    finish();
                    return true;

                case R.id.nav_profile:
                    // Handle Profile action
                    return true;
            }
            return false;
        });
    }
}
