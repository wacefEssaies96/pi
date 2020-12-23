package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

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

public class SplachScreenActivity extends AppCompatActivity {
    ArrayList<String> data=new ArrayList<>();
    ArrayList<String> images=new ArrayList<>();
    String url = "http://abd5e1f50d3b.ngrok.io/api/list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        StringRequest req = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Successful Response: ", response);
                    JSONArray array = null;
                    try {
                        array = new JSONArray(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = null;
                        try {
                            object = array.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String name = null;
                        try {
                            name = object.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String image = null;
                        try {
                            image = object.getString("image");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        images.add(image);
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
        req.setRetryPolicy(new DefaultRetryPolicy(
                13000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(req);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("data",data);
                i.putExtra("images",images);
                startActivity(i);
                finish();
            }
        },50000);
    }
}