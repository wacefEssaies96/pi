package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://192.168.1.11:8088/users";
    ArrayList<User> listuser = new ArrayList<>();
    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pwd = password.getText().toString();
                if(user.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all the blanks.",Toast.LENGTH_LONG).show();
                }else{

                    //Toast.makeText(getApplicationContext(),bCryptHashString,Toast.LENGTH_LONG).show();
                    searchUser(user,pwd);

                }
            }
        });

        //loadlist();

    }
    private void searchUser(final String username, final String pass){
        final StringRequest stringRequest = new
                StringRequest(Request.Method.GET,"http://192.168.1.11:8088/user/"+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject object;
                        String bCryptHashString = BCrypt.withDefaults().hashToString(12,pass.toCharArray());
                        try {
                            object = new JSONObject(response);
                            String p = object.getString("password");
                            BCrypt.Result result = BCrypt.verifyer().verify("wacef".toCharArray(),bCryptHashString);
                            if(result.verified){
                                Toast.makeText(getApplicationContext(),"vous etes connecté",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_LONG).show();
                            }
//                            if(object.getString("password").equals(pass)){
//                                Toast.makeText(getApplicationContext(),"vous etes connecté",Toast.LENGTH_LONG).show();
//                            }else{
//                                Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_LONG).show();
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage()+" error Loading Users",Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void loadlist() {
        StringRequest stringRequest = new
                StringRequest(Request.Method.GET,URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int i =0;i<array.length();i++){
                            JSONObject object = null;
                            try {
                                object = array.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            int id=0;
                            try {
                                id = object.getInt("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String username = null;
                            try {
                                username = object.getString("username");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String password = null;
                            try {
                                password = object.getString("password");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            User user = new User(id,username,password);
                            listuser.add(user);
                            Toast.makeText(getApplicationContext(),listuser.get(0).getUsername(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage()+" error Loading Users",Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}