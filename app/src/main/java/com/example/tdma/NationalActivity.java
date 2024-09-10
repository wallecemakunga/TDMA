package com.example.tdma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NationalActivity extends AppCompatActivity {

    RecyclerView parksRecyclerView;
    String[] parks = {
            "Serengeti National Park",
            "Ngorongoro Conservation Area",
            "Tarangire National Park",
            "Lake Manyara National Park",
            "Mikumi National Park",
            "Ruaha National Park",
            "Katavi National Park",
            "Gombe Stream National Park"
    };

    String[] parkDetails = {
            "Serengeti National Park: Known for its annual migration of over 1.5 million white-bearded wildebeest and 250,000 zebra.",
            "Ngorongoro Conservation Area: A UNESCO World Heritage Site, known for the Ngorongoro Crater.",
            "Tarangire National Park: Famous for its large elephant herds and ancient baobab trees.",
            "Lake Manyara National Park: Known for its tree-climbing lions and soda ash lake attracting thousands of flamingos.",
            "Mikumi National Park: Offers great wildlife viewing, with many species similar to those found in the Serengeti.",
            "Ruaha National Park: Known for its large population of elephants and the Great Ruaha River.",
            "Katavi National Park: Offers a true wilderness experience with its floodplains and seasonal lakes.",
            "Gombe Stream National Park: Famous for the research of Jane Goodall on chimpanzee populations."
    };

    int[][] parkImageUrls = {
            {R.drawable.serengeti1, R.drawable.serengeti2},
            {R.drawable.ngorongoro1, R.drawable.manyara1},
            {R.drawable.serengeti1, R.drawable.tarangire2},
            {R.drawable.manyara1, R.drawable.manyara2},
            {R.drawable.mikumi1, R.drawable.mikumi2},
            {R.drawable.ruaha1, R.drawable.ruaha2},
            {R.drawable.katavi1, R.drawable.katavi2},
            {R.drawable.gombe1, R.drawable.gombe2}
    };

    private ParksAdapter parksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national);

        parksRecyclerView = findViewById(R.id.parksRecyclerView);
        parksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parksAdapter = new ParksAdapter(parks, parkDetails, parkImageUrls);
        parksRecyclerView.setAdapter(parksAdapter);

        // Set up automatic image slideshow
        setupImageSlideshow();

        // Set up Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(NationalActivity.this, HomeActivity.class));
                    return true;
                case R.id.nav_logout:
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(NationalActivity.this, LoginActivity.class));
                    finish();
                    return true;
                case R.id.nav_profile:
                    // Handle Profile action (implement if needed)
                    return true;
            }
            return false;
        });
    }

    private void setupImageSlideshow() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable imageSliderRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < parksAdapter.getItemCount(); i++) {
                    int nextPage = (parksAdapter.getCurrentPage(i) + 1) % parkImageUrls[i].length;
                    parksAdapter.setCurrentPage(i, nextPage);
                    parksAdapter.notifyItemChanged(i);
                }
                handler.postDelayed(this, 5000); // 5 seconds delay
            }
        };
        handler.postDelayed(imageSliderRunnable, 7000); // Start slideshow after 5 seconds initially
    }
}
