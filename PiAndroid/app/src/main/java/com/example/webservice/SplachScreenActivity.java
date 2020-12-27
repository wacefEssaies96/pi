package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplachScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        Intent i = getIntent();
        String value = i.getStringExtra("value");

        if(value.equals("login")){
            login(i.getStringExtra("username"),i.getStringExtra("password"));
        }
        if(value.equals("products")){
            loadingProducts();
        }
        if(value.equals("customers")){
            loadingCustomers();
        }
        if(value.equals("addCustomer")){
            addCustomer(getIntent().getStringExtra("newName"),getIntent().getStringExtra("newEmail"),getIntent().getStringExtra("newPhone"));
        }
        if(value.equals("editCustomer")){
            editCustomer(getIntent().getStringExtra("name"),getIntent().getStringExtra("editName"),getIntent().getStringExtra("editEmail"),getIntent().getStringExtra("editPhone"));
        }
        if(value.equals("deleteCustomer")){
            deleteCustomer(getIntent().getStringExtra("name"));
        }

    }

    private void deleteCustomer(String name) {
        SharedPreferences nPre = PreferenceManager.getDefaultSharedPreferences(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", nPre.getString(getString(R.string.name),""));
            jsonObject.put("password", nPre.getString(getString(R.string.password),""));
            jsonObject.put("name",name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getResources().getString(R.string.api_url)+"/api/deleteUser", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Successful Response: ", "Success");
                        try {
                            int status = response.getInt("status");
                            if(status == 1){
                                loadingCustomers();
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid !",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        request.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(this).add(request);

        //loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),IsetActivity.class);
                startActivity(i);
                finish();
            }
        },15000);
    }

    private void editCustomer(String name,String editName, String editEmail, String editPhone) {
        SharedPreferences nPre = PreferenceManager.getDefaultSharedPreferences(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", nPre.getString(getString(R.string.name),""));
            jsonObject.put("password", nPre.getString(getString(R.string.password),""));
            jsonObject.put("name",name);
            jsonObject.put("updatedName",editName);
            jsonObject.put("updatedEmail",editEmail);
            jsonObject.put("updatedPhone",editPhone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getResources().getString(R.string.api_url)+"/api/updateUser", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Successful Response: ", "Success");
                        try {
                            int status = response.getInt("status");
                            if(status == 1){
                                loadingCustomers();
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid !",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        request.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(this).add(request);

        //loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),IsetActivity.class);
                startActivity(i);
                finish();
            }
        },15000);
    }

    private void addCustomer(String newName,String newEmail,String newPhone){
        SharedPreferences nPre = PreferenceManager.getDefaultSharedPreferences(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", nPre.getString(getString(R.string.name),""));
            jsonObject.put("password", nPre.getString(getString(R.string.password),""));
            jsonObject.put("name",newName);
            jsonObject.put("email",newEmail);
            jsonObject.put("phone",newPhone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getResources().getString(R.string.api_url)+"/api/createUser", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Successful Response: ", "Success");
                        try {
                            int status = response.getInt("status");
                            if(status == 1){
                                loadingCustomers();
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid !",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        request.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(this).add(request);

        //loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),IsetActivity.class);
                startActivity(i);
                finish();
            }
        },15000);
    }
    private void loadingCustomers() {
        final ArrayList<String> nameArray = new ArrayList<>();
        final ArrayList<String> emailArray = new ArrayList<>();
        final ArrayList<String> phoneArray = new ArrayList<>();
        final ArrayList<String> images = new ArrayList<>();
        SharedPreferences nPre = PreferenceManager.getDefaultSharedPreferences(this);
        //optional
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", nPre.getString(getString(R.string.name),""));
            jsonObject.put("password", nPre.getString(getString(R.string.password),""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray json = new JSONArray();
        json.put(jsonObject);

        //creating request to get list of products
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, getResources().getString(R.string.api_url)+"/api/users", json,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Log.d("Successful Response: ", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object;
                            String name = null, email = null, phone = null;
                            String image = null;
                            String test = null;
                            try {
                                object = response.getJSONObject(i);
                                //getting strings from jsonObject
                                name = object.getString("name");
                                email = object.getString("email");
                                phone = object.getString("phone");
                                image = object.getString("image");
                                test = image.replaceAll("\\'","");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //adding strings to ArrayLists
                            images.add(test);
                            nameArray.add(name);
                            emailArray.add(email);
                            phoneArray.add(phone);
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
                Intent i = new Intent(getApplicationContext(),CustomersActivity.class);
                i.putExtra("name",nameArray);
                i.putExtra("email",emailArray);
                i.putExtra("phone",phoneArray);
                i.putExtra("images",images);
                startActivity(i);
                finish();
            }
        },20000);
    }

    private void login(final String user, final String pwd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", pwd);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, getResources().getString(R.string.api_url)+"/api/login", params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Successful Response: ", "Success");
                        try {
                            int status = response.getInt("status");
                            if(status == 1){
                                Intent i = new Intent(getApplicationContext(),IsetActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        Volley.newRequestQueue(this).add(jsObjRequest);
        //loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),IsetActivity.class);
                startActivity(i);
                finish();
            }
        },6000);
    }


    private void loadingProducts(){
        final ArrayList<String> data = new ArrayList<>();
        final ArrayList<String> prices = new ArrayList<>();
        final ArrayList<String> images = new ArrayList<>();
        SharedPreferences nPre = PreferenceManager.getDefaultSharedPreferences(this);
        //optional
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", nPre.getString(getString(R.string.name),""));
            jsonObject.put("password", nPre.getString(getString(R.string.password),""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray json = new JSONArray();
        json.put(jsonObject);

        //creating request to get list of products
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, getResources().getString(R.string.api_url)+"/api/list", json,
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
                15000,
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
        },15000);
    }
}