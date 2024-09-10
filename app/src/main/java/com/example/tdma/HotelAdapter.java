package com.example.tdma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private Context context;
    private List<Hotel> hotelList;

    public HotelAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);

        holder.textViewHotelName.setText(hotel.getName());
        holder.textViewHotelDescription.setText(hotel.getDescription());
        holder.textViewHotelPrice.setText(hotel.getPrice());

        // Set up ViewPager with HotelImageAdapter
        HotelImageAdapter imageAdapter = new HotelImageAdapter(context, hotel.getImageResIds());
        holder.viewPagerHotel.setAdapter(imageAdapter);

        // Handle Back Arrow Click
        holder.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = holder.viewPagerHotel.getCurrentItem();
                int previousItem = currentItem - 1;
                if (previousItem < 0) {
                    previousItem = hotel.getImageResIds().size() - 1; // wrap around to the last image
                }
                holder.viewPagerHotel.setCurrentItem(previousItem, true);
            }
        });

        // Handle Next Arrow Click
        holder.imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = holder.viewPagerHotel.getCurrentItem();
                int nextItem = currentItem + 1;
                if (nextItem >= hotel.getImageResIds().size()) {
                    nextItem = 0; // wrap around to the first image
                }
                holder.viewPagerHotel.setCurrentItem(nextItem, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPagerHotel;
        TextView textViewHotelName, textViewHotelDescription, textViewHotelPrice;
        ImageView imageViewBack, imageViewNext;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPagerHotel = itemView.findViewById(R.id.viewPagerHotel);
            textViewHotelName = itemView.findViewById(R.id.textViewHotelName);
            textViewHotelDescription = itemView.findViewById(R.id.textViewHotelDescription);
            textViewHotelPrice = itemView.findViewById(R.id.textViewHotelPrice);
            imageViewBack = itemView.findViewById(R.id.imageViewBack);
            imageViewNext = itemView.findViewById(R.id.imageViewNext);
        }
    }
}
