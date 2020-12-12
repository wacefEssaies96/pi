package com.example.webservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<User> listuser;
    Context ct;

    public MyAdapter(Context ct,ArrayList<User> listuser){
        this.listuser = listuser;
        this.ct = ct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user.setText(String.valueOf(listuser.get(position).getId()));
        holder.username.setText(listuser.get(position).getUsername());
        holder.email.setText(listuser.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user,username,email;
        MyAdapter myAdapter;
        public MyViewHolder(@NonNull View itemView, MyAdapter myAdapter) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            this.myAdapter = myAdapter;

        }
    }
}
