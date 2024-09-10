package com.example.tdma;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

public class ApartmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewPager2 viewPager2;
    ImageSliderAdapter sliderAdapter;
    List<Integer> imageList;
    Handler handler;
    Runnable imageSliderRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);

        // RecyclerView setup
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for apartments
        List<Apartment> apartmentList = Arrays.asList(
                new Apartment("Aura Suites", " Morogoro Road Upanga East, Upanga East, Dar es Salaam, Tanzania • 1 bathroom • 1 kitchen • 59m²\n" +
                        "1 queen bed\n" +
                        "Free cancellation\n" +
                        "No prepayment needed – pay at the property\n" +
                        "1 night, 2 adults\n" +
                        "TZS 357,079\n" +
                        "Contacts:0787638627", R.drawable.aura),
                new Apartment("EliteOysterbay", "Location:Chole Road Oyster Bay,Dar es salaam-Tanzania\n"+"Two-Bedroom Apartment\n" +
                        "Entire apartment • 2 bedrooms • 2 bathrooms • 100m²\n" +
                        "2 beds (1 full, 1 queen)\n" +
                        "1 night, 2 adults\n" +
                        "TZS 190,971\n" +
                        "+TZS 7,935 taxes and fees\n" + "Contact:0787638627", R.drawable.osterbay),

                new Apartment("Nyumbani Residence Apartments", "Location:Jambiani-Kibigija,Jambiani,Tanzania\n"+"One-Bedroom Villa\n" +
                        "Entire villa • 1 bedroom • 1 living room • 1 bathroom • 1 kitchen • 70m²\n" +
                        "3 beds (1 king, 2 sofa beds)\n" +
                        "Breakfast included\n" +
                        "Only 5 left at this price on our site\n" +
                        "1 night, 2 adults\n" +
                        "TZS 502,555TZS 301,533\n" +
                        "Original price TZS 502,555. Current price TZS 301,533.\n" +
                        "+TZS 10,580 taxes and fees\n"+"Contact:0787638627", R.drawable.nyumbani),

                new Apartment("Mikocheni Condo Hotel & Apartment", "Deluxe Double Room\n" +
                        "1 queen bed\n" +
                        "Free cancellation\n" +
                        "No prepayment needed – pay at the property\n" +
                        "Only 6 rooms left at this price on our site\n" +
                        "1 night, 2 adults\n" +
                        "TZS 87,947Price TZS 87,947\n" +
                        "+TZS 7,935 taxes and fees\n" + "Contact:0787638627", R.drawable.condo),

                new Apartment("Rayan Apartments & Safaris", "Location:duluti,Shangarai,Arusha Tanzania\n"+"One-Bedroom Apartment\n" +
                        "Entire apartment • 1 bedroom • 1 living room • 1 bathroom • 1 kitchen • 30m²\n" +
                        "1 queen bed\n" +
                        "Only 1 left at this price on our site\n" +
                        "1 night, 2 adults\n" +
                        "TZS 148,121TZS 103,685\n" +
                        "Original price TZS 148,121. Current price TZS 103,685.\n" +
                        "Includes taxes and fees\n"+"contact:0787123123", R.drawable.rayan)
                // Add more apartments as needed
        );

        ApartmentAdapter adapter = new ApartmentAdapter(this, apartmentList);
        recyclerView.setAdapter(adapter);

        // ViewPager2 setup
        viewPager2 = findViewById(R.id.viewPager);

        // Sample data for image slider
        imageList = Arrays.asList(
                R.drawable.aura,
                R.drawable.osterbay,
                R.drawable.condo,
                R.drawable.nyumbani,
                R.drawable.rayan

                // Add more image resources as needed
        );

        sliderAdapter = new ImageSliderAdapter(this, imageList);
        viewPager2.setAdapter(sliderAdapter);

        // Initialize handler and runnable for auto image slider
        handler = new Handler(Looper.getMainLooper());
        imageSliderRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager2.getCurrentItem();
                int nextItem = (currentItem + 1) % imageList.size();
                viewPager2.setCurrentItem(nextItem);
                handler.postDelayed(this, 5000); // 5 seconds delay
            }
        };

        // Start auto image slider
        startImageSlider();
    }

    private void startImageSlider() {
        // Start the image slider runnable for automatic sliding
        handler.postDelayed(imageSliderRunnable, 5000); // 5 seconds delay
    }

    @Override
    protected void onDestroy() {
        // Remove callbacks to prevent memory leaks
        handler.removeCallbacks(imageSliderRunnable);
        super.onDestroy();
    }
}
