package com.example.tdma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MountainActivity extends AppCompatActivity {

    RecyclerView mountainsRecyclerView;

    // Updated arrays to have consistent lengths (5 elements each)
    String[] mountains = {
            "Mount Kilimanjaro",
            "Mount Meru",
            "Oldonyo Lengai (Mountain of God)",
            "Mount Hanang",
            "Usambara Mountains"
    };

    String[] mountainDetails = {
            "Mount Kilimanjaro: Africa's highest peak and the tallest freestanding mountain in the world.",
            "Mount Meru: The second-highest mountain in Tanzania, located in Arusha National Park.",
            "Oldonyo Lengai: An active volcano known for its unique lava that turns white upon eruption.",
            "Mount Hanang: A solitary volcanic cone rising dramatically from the Tanzanian savanna.",
            "Usambara Mountains: Known for their rich biodiversity and picturesque views."
    };

    int[][] mountainImageUrls = {
            {R.drawable.kilimanjaro1, R.drawable.kilimanjaro2},
            {R.drawable.meruu, R.drawable.meru2},
            {R.drawable.oldonyo, R.drawable.oldonyo2},
            {R.drawable.udzungwa1, R.drawable.udzungwa2},
            {R.drawable.usambara, R.drawable.usambara2}
    };

    private MountainsAdapter mountainsAdapter;
    private Handler handler;
    private Runnable imageSliderRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        mountainsRecyclerView = findViewById(R.id.mountainsRecyclerView);
        mountainsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mountainsAdapter = new MountainsAdapter(mountains, mountainDetails, mountainImageUrls);
        mountainsRecyclerView.setAdapter(mountainsAdapter);

        // Set up automatic image slideshow
        setupImageSlideshow();

        // Set up Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(MountainActivity.this, HomeActivity.class));
                    return true;
                case R.id.nav_logout:
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(MountainActivity.this, LoginActivity.class));
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
        handler = new Handler(Looper.getMainLooper());
        imageSliderRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mountainsAdapter.getItemCount(); i++) {
                    if (mountainImageUrls[i].length == 0) continue; // Skip if no images
                    int nextPage = (mountainsAdapter.getCurrentPage(i) + 1) % mountainImageUrls[i].length;
                    mountainsAdapter.setCurrentPage(i, nextPage);
                }
                handler.postDelayed(this, 5000); // 5 seconds delay for the next run
            }
        };

        handler.postDelayed(imageSliderRunnable, 5000); // Start slideshow after 5 seconds initially

        // Stop the handler when the activity is destroyed
        getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onPause(@NonNull LifecycleOwner owner) {
                handler.removeCallbacks(imageSliderRunnable);
            }

            @Override
            public void onDestroy(@NonNull LifecycleOwner owner) {
                handler.removeCallbacks(imageSliderRunnable);
            }

            @Override
            public void onResume(@NonNull LifecycleOwner owner) {
                handler.postDelayed(imageSliderRunnable, 5000); // Resume slideshow with a 5-second delay
            }
        });
    }
}
