package com.example.tdma;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HotelDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        // Get the hotel name from the intent
        String hotelName = getIntent().getStringExtra("HOTEL_NAME");

        // Set the hotel name to a TextView
        TextView hotelNameTextView = findViewById(R.id.hotel_name_detail);
        hotelNameTextView.setText(hotelName);

        // You can also set other hotel details here if you pass them through the intent
    }
}
