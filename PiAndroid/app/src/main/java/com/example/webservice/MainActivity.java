package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pwd = password.getText().toString();
                if(user.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all the blanks.",Toast.LENGTH_LONG).show();
                }else{
                    searchUser(user,pwd);
                }
            }
        });
    }
    private void searchUser(final String username, final String pass){
        final StringRequest stringRequest = new
                StringRequest(Request.Method.GET,"http://192.168.1.6:8088/user/"+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject object;
                        try {
                            object = new JSONObject(response);
                            String p = object.getString("password");
                            BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(),p);
                            if(result.verified){
                                Toast.makeText(getApplicationContext(),"vous etes connecté",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_LONG).show();
                            }
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

}