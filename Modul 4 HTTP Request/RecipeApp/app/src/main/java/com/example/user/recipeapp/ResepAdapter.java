package com.example.user.recipeapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ViewHolder> {

    private List<Resep> resepList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, calories;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.resepTitle);
            calories = (TextView) view.findViewById(R.id.resepCalories);
            image = (ImageView) view.findViewById(R.id.resepImage);
        }
    }

    public ResepAdapter(List<Resep> resepList) {
        this.resepList = resepList;
    }

    @Override
    public ResepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_resep, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resep resep = resepList.get(position);
        holder.title.setText(resep.getTitle());
        holder.calories.setText(resep.getCalories());
        Picasso.get()
                .load(resep.getImage())
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return resepList.size();
    }
}