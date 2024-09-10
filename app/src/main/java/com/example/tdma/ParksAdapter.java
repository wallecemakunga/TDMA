package com.example.tdma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class ParksAdapter extends RecyclerView.Adapter<ParksAdapter.ParkViewHolder> {

    private final String[] parkNames;
    private final String[] parkDetails;
    private final int[][] parkImageUrls;
    private int[] currentPage;

    public ParksAdapter(String[] parkNames, String[] parkDetails, int[][] parkImageUrls) {
        this.parkNames = parkNames;
        this.parkDetails = parkDetails;
        this.parkImageUrls = parkImageUrls;
        this.currentPage = new int[parkNames.length];
    }

    @NonNull
    @Override
    public ParkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.park_item, parent, false);
        return new ParkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkViewHolder holder, int position) {
        holder.parkNameTextView.setText(parkNames[position]);
        holder.parkDetailTextView.setText(parkDetails[position]);
        holder.viewPager.setAdapter(new ImageSliderAdapter(parkImageUrls[position]));
        holder.viewPager.setCurrentItem(currentPage[position]);
    }

    @Override
    public int getItemCount() {
        return parkNames.length;
    }

    public int getCurrentPage(int position) {
        return currentPage[position];
    }

    public void setCurrentPage(int position, int page) {
        currentPage[position] = page;
    }

    static class ParkViewHolder extends RecyclerView.ViewHolder {
        TextView parkNameTextView;
        TextView parkDetailTextView;
        ViewPager2 viewPager;

        public ParkViewHolder(@NonNull View itemView) {
            super(itemView);
            parkNameTextView = itemView.findViewById(R.id.parkNameTextView);
            parkDetailTextView = itemView.findViewById(R.id.parkDetailTextView);
            viewPager = itemView.findViewById(R.id.viewPager);
        }
    }

    static class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

        private final int[] images;

        public ImageSliderAdapter(int[] images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_item, parent, false);
            return new ImageSliderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
            holder.imageView.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        static class ImageSliderViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ImageSliderViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
}
