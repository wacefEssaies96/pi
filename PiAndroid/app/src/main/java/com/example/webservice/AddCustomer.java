package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCustomer extends AppCompatActivity {
    EditText name,email,phone;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        name = findViewById(R.id.newName);
        email = findViewById(R.id.newEmail);
        phone = findViewById(R.id.newPhone);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SplachScreenActivity.class);
                i.putExtra("value","addCustomer");
                i.putExtra("newName",name.getText().toString());
                i.putExtra("newEmail",email.getText().toString());
                i.putExtra("newPhone",phone.getText().toString());
                startActivity(i);
                finish();
            }
        });

    }
}