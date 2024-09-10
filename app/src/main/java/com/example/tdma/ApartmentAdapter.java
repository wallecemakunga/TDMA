package com.example.tdma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder> {

    private Context context;
    private List<Apartment> apartments;

    public ApartmentAdapter(Context context, List<Apartment> apartments) {
        this.context = context;
        this.apartments = apartments;
    }

    @NonNull
    @Override
    public ApartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.apartment_item, parent, false);
        return new ApartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentViewHolder holder, int position) {
        Apartment apartment = apartments.get(position);
        holder.tvApartmentName.setText(apartment.getName());
        holder.tvApartmentDescription.setText(apartment.getDescription());
        holder.ivApartmentImage.setImageResource(apartment.getImageResId()); // Load image using resource ID
    }

    @Override
    public int getItemCount() {
        return apartments.size();
    }

    public static class ApartmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvApartmentName, tvApartmentDescription;
        ImageView ivApartmentImage;

        public ApartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApartmentName = itemView.findViewById(R.id.tvApartmentName);
            tvApartmentDescription = itemView.findViewById(R.id.tvApartmentDescription);
            ivApartmentImage = itemView.findViewById(R.id.ivApartmentImage);
        }
    }
}
