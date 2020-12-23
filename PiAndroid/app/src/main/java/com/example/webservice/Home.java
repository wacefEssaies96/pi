package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
        Intent i = getIntent();
        ArrayList<String> data = i.getStringArrayListExtra("data");
        ArrayList<String> images = i.getStringArrayListExtra("images");
        Log.d("data : ",data.toString());
        myrecycler=findViewById(R.id.rv);
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),data,images);
        myrecycler.setAdapter(myAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}