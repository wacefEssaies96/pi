package com.example.webservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<String> products,prices;
    ArrayList<Bitmap> images;
    Context ct;

    public MyAdapter(Context ct,ArrayList<String> products,ArrayList<String> prices,ArrayList<Bitmap> images){
        this.products = products;
        this.prices = prices;
        this.images = images;
        this.ct = ct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(products.get(position));
        holder.price.setText("$ "+prices.get(position));
        holder.image.setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        MyAdapter myAdapter;
        ImageView image;
        public MyViewHolder(@NonNull View itemView, MyAdapter myAdapter) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            this.myAdapter = myAdapter;
        }
    }
}
