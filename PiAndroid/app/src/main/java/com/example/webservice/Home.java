package com.example.webservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    MyAdapter myAdapter;
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
        myAdapter = new MyAdapter(getApplicationContext(),data,prices,decodedList);
        myrecycler.setAdapter(myAdapter);
        myrecycler.setLayoutManager(new GridLayoutManager(this,3));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu,menu);
        // Associate searchable configuration with the SearchView
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewImages:
                Toast.makeText(this, "Menu ViewImages selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.products:
                Intent i = new Intent(getApplicationContext(),SplachScreenActivity.class);
                i.putExtra("value","products");
                startActivity(i);
                finish();
                break;
            case R.id.customers:
                Intent intent = new Intent(getApplicationContext(),SplachScreenActivity.class);
                intent.putExtra("value","customers");
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}