package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private RecyclerView myrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        //getting data from Intent
        ArrayList<String> data = intent.getStringArrayListExtra("data");
        ArrayList<String> prices = intent.getStringArrayListExtra("prices");
        ArrayList<String> images = intent.getStringArrayListExtra("images");
        //decoding images to bitmap
        ArrayList<Bitmap> decodedList = new ArrayList<>();
        for(int i = 0;i<images.size();i++){
            byte[] decodedString = Base64.decode(images.get(i), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            decodedList.add(decodedByte);
        }
        //setting recycler view
        myrecycler=findViewById(R.id.rv);
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),data,prices,decodedList);
        myrecycler.setAdapter(myAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}