package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplachScreenActivity extends AppCompatActivity {
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    String url = "http://26488b5e11e0.ngrok.io/api/list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        //optional
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "wacef.stratrait@gmail.com");
            jsonObject.put("password", "admin");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray json = new JSONArray();
        json.put(jsonObject);

        //creating request to get list of products
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       // Log.d("Successful Response: ", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object;
                            String name = null, price = null;
                            String image = null;
                            String test = null;
                            try {
                                object = response.getJSONObject(i);
                                //getting strings from jsonObject
                                name = object.getString("name");
                                price = object.getString("list_price");
                                image = object.getString("image");
                                test = image.replaceAll("\\'","");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //adding strings to ArrayLists
                            images.add(test);
                            prices.add(price);
                            data.add(name);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError response) {
                        Log.d("Error Response: ", response.toString());
                    }
                }
        );

        //making timeout
        req.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(this).add(req);

        //loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("data",data);
                i.putExtra("prices",prices);
                i.putExtra("images",images);
                startActivity(i);
                finish();
            }
        },20000);
    }
}