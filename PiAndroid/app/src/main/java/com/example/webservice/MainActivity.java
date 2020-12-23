package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private SharedPreferences nPre;
    private SharedPreferences.Editor nEditor;
    EditText tusername,tpassword;
    private CheckBox nCheckBox;
    Button login,register;
    String url = "http://26488b5e11e0.ngrok.io/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tusername = findViewById(R.id.username);
        tpassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        nCheckBox = findViewById(R.id.rem);
        register = findViewById(R.id.register);

        nPre = PreferenceManager.getDefaultSharedPreferences(this);
        nEditor = nPre.edit();
        checkSharedPreferences();
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
                final String user = tusername.getText().toString();
                final String pwd = tpassword.getText().toString();
                if(user.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all the blanks.",Toast.LENGTH_LONG).show();
                }else{
                    if(nCheckBox.isChecked()){
                        nEditor.putString(getString(R.string.chekbox),"True");
                        nEditor.apply();

                        String name = tusername.getText().toString();
                        nEditor.putString(getString(R.string.name),name);
                        nEditor.commit();

                        String password = tpassword.getText().toString();
                        nEditor.putString(getString(R.string.password),password);
                    }else{
                        nEditor.putString(getString(R.string.chekbox),"False");
                    }
                    nEditor.commit();
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
    }

    private void checkSharedPreferences(){

        String checkbox = nPre.getString(getString(R.string.chekbox),"False");
        String name = nPre.getString(getString(R.string.name),"");
        String password = nPre.getString(getString(R.string.password),"");

        tusername.setText(name);
        tpassword.setText(password);
        nCheckBox.setChecked(checkbox.equals("True"));
    }

}