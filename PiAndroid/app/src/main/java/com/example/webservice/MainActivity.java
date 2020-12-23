package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,register;
    String url = "http://abd5e1f50d3b.ngrok.io/api/login";

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
                final String user = username.getText().toString();
                final String pwd = password.getText().toString();
                if(user.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all the blanks.",Toast.LENGTH_LONG).show();
                }else{
                    log(user,pwd);
                }
            }
        });
    }

    private void log(final String user, final String pwd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", pwd);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Successful Response: ", response.toString());
                    try {
                        int status = response.getInt("status");
                        if(status == 1){
                            Intent i = new Intent(getApplicationContext(),SplachScreenActivity.class);
                            startActivity(i);
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