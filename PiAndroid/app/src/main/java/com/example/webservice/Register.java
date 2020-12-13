package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;

import at.favre.lib.crypto.bcrypt.BCrypt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    EditText username,password;
    Button save;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String url = "http://192.168.1.6:8088/save/user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            saveUser(username.getText().toString(),password.getText().toString());
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
    }

    private void saveUser(final String username, final String pass) throws JSONException, IOException {
        String bCryptHashString = BCrypt.withDefaults().hashToString(12, pass.toCharArray());
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", bCryptHashString);
        post(json.toString());
    }
    void post(String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Objects.requireNonNull(response.body()).string();
        }
    }

}