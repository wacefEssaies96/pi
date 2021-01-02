package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomersActivity extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        fab = findViewById(R.id.addCustomer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),AddCustomer.class);
                startActivity(in);
            }
        });

        //getting data from Intent

        final ArrayList<String> name = getIntent().getStringArrayListExtra("name");
        final ArrayList<String> email = getIntent().getStringArrayListExtra("email");
        final ArrayList<String> phone = getIntent().getStringArrayListExtra("phone");
        ArrayList<String> images = getIntent().getStringArrayListExtra("images");

        //decoding images to bitmap

        final ArrayList<Bitmap> decodedList = new ArrayList<>();
        for(int i = 0;i<images.size();i++){
            byte[] decodedString = Base64.decode(images.get(i), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            decodedList.add(decodedByte);
        }
        //setting recycler view
        AdapterForCustomers.ItemClickListener listener = new AdapterForCustomers.ItemClickListener() {
            @Override
            public void onItemClicked(View v, final int selectedPos, CardView cardView,final TextView editName, final TextView editEmail, final TextView editPhone) {
                registerForContextMenu(cardView);
                final PopupMenu popmenu = new PopupMenu(getApplicationContext(),v);
                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.editCustomer:{
                                Intent intent = new Intent(getApplicationContext(),EditCustomer.class);
                                intent.putExtra("name",editName.getText().toString());
                                intent.putExtra("email",editEmail.getText().toString());
                                intent.putExtra("phone",editPhone.getText().toString());
                                startActivity(intent);
                            }
                            case R.id.deleteCustomer:{
                                Intent intent = new Intent(getApplicationContext(),SplachScreenActivity.class);
                                intent.putExtra("value","deleteCustomer");
                                intent.putExtra("name",editName.getText().toString());
                                startActivity(intent);
                            }

                        }
                        return false;
                    }
                });
                MenuInflater inflater=getMenuInflater();
                inflater.inflate(R.menu.popmenu_customers,popmenu.getMenu());
                popmenu.show();
            }
        };
        RecyclerView rv = findViewById(R.id.rvCustomers);
        AdapterForCustomers ad = new AdapterForCustomers(getApplicationContext(),name,email,phone,decodedList,listener);
        rv.setAdapter(ad);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}