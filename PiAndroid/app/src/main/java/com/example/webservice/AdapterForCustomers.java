package com.example.webservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForCustomers extends RecyclerView.Adapter<AdapterForCustomers.CustomersViewHolder> {
    private final ItemClickListener listener;
    ArrayList<String> nameArray,emailArray,phoneArray;
    ArrayList<Bitmap> imagesArray;
    Context ct;

    public AdapterForCustomers(Context ct,ArrayList<String> nameArray,ArrayList<String> emailArray,ArrayList<String> phoneArray,ArrayList<Bitmap> imagesArray,ItemClickListener listener){
        this.nameArray = nameArray;
        this.emailArray = emailArray;
        this.phoneArray = phoneArray;
        this.imagesArray = imagesArray;
        this.ct = ct;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne_customers, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersViewHolder holder, int position) {
        holder.name.setText(nameArray.get(position));
        holder.email.setText(emailArray.get(position));
        holder.phone.setText(phoneArray.get(position));
        holder.images.setImageBitmap(imagesArray.get(position));
        holder.bind(listener,position);
    }

    @Override
    public int getItemCount() {
        return nameArray.size();
    }

    public static class CustomersViewHolder extends RecyclerView.ViewHolder{
        TextView name,email,phone;
        CardView cardview;
        ImageView images;
        AdapterForCustomers adapterForCustomers;
        public CustomersViewHolder(@NonNull View itemView, AdapterForCustomers adapterForCustomers) {
            super(itemView);
            this.adapterForCustomers = adapterForCustomers;
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            images = itemView.findViewById(R.id.imageCustomer);
            cardview = itemView.findViewById(R.id.carviewCustomer);
        }

        public void bind(final ItemClickListener listener, final int position) {
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(v,position,cardview,name,email,phone);
                }
            });
        }
    }
    public interface ItemClickListener {
        void onItemClicked(View v, int selectedPos, CardView cardView, TextView name, TextView email, TextView phone);
    }
}
