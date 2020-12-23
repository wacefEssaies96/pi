package com.example.webservice;

import android.content.Context;
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

    ArrayList<String> products,images;
    Context ct;

    public MyAdapter(Context ct,ArrayList<String> products,ArrayList<String> images){
        this.products = products;
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
        //holder.image.(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        MyAdapter myAdapter;
        ImageView image;
        public MyViewHolder(@NonNull View itemView, MyAdapter myAdapter) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            this.myAdapter = myAdapter;
        }
    }
}
