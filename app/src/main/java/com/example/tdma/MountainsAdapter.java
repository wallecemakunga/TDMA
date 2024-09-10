package com.example.tdma;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MountainsAdapter extends RecyclerView.Adapter<MountainsAdapter.ViewHolder> {

    private static final String TAG = "MountainsAdapter";

    private String[] mountains;
    private String[] mountainDetails;
    private int[][] mountainImageUrls;
    private int[] currentPages; // Array to track current page for each mountain item

    public MountainsAdapter(String[] mountains, String[] mountainDetails, int[][] mountainImageUrls) {
        this.mountains = mountains;
        this.mountainDetails = mountainDetails;
        this.mountainImageUrls = mountainImageUrls;
        this.currentPages = new int[mountains.length]; // Initialize currentPages array
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mountainView = inflater.inflate(R.layout.item_mountain, parent, false);
        return new ViewHolder(mountainView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mountainName.setText(mountains[position]);
        holder.mountainDetails.setText(mountainDetails[position]);

        // Ensure currentPages index is within the bounds of the image array for this mountain
        if (currentPages[position] >= mountainImageUrls[position].length) {
            currentPages[position] = 0; // Reset to the first image if out of bounds
        }

        holder.mountainImage.setImageResource(mountainImageUrls[position][currentPages[position]]);

        // Log the current state for debugging
        Log.d(TAG, "onBindViewHolder: position=" + position + ", currentPage=" + currentPages[position]);
    }

    @Override
    public int getItemCount() {
        return mountains.length;
    }

    public int getCurrentPage(int position) {
        if (position < 0 || position >= currentPages.length) {
            // Log error and return default value to avoid out-of-bounds access
            Log.e(TAG, "getCurrentPage: Invalid position " + position);
            return 0;
        }
        return currentPages[position];
    }

    public void setCurrentPage(int position, int page) {
        if (position < 0 || position >= currentPages.length) {
            // Log error to avoid setting an invalid position
            Log.e(TAG, "setCurrentPage: Invalid position " + position);
            return;
        }
        if (page < 0 || page >= mountainImageUrls[position].length) {
            // Log error to avoid setting an invalid page
            Log.e(TAG, "setCurrentPage: Invalid page " + page + " for position " + position);
            return;
        }
        currentPages[position] = page;
        notifyItemChanged(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mountainName;
        public TextView mountainDetails;
        public ImageView mountainImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mountainName = itemView.findViewById(R.id.textMountainName);
            mountainDetails = itemView.findViewById(R.id.textMountainDetails);
            mountainImage = itemView.findViewById(R.id.imageMountain);
        }
    }
}
