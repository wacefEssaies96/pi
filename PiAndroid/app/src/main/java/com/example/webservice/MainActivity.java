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
import android.widget.Space;
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
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tusername = findViewById(R.id.username);
        tpassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        nCheckBox = findViewById(R.id.rem);

        nPre = PreferenceManager.getDefaultSharedPreferences(this);
        nEditor = nPre.edit();
        checkSharedPreferences();

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
                    Intent intent = new Intent(getApplicationContext(), SplachScreenActivity.class);
                    intent.putExtra("value","login");
                    intent.putExtra("username",user);
                    intent.putExtra("password",pwd);
                    startActivity(intent);
                }
            }
        });
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