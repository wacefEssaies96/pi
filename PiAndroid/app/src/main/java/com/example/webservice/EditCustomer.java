package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EditCustomer extends AppCompatActivity {
    TextView tName,tEmail,tPhone;
    ImageView imageView;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);
        final String name = getIntent().getStringExtra("name");
        final String email = getIntent().getStringExtra("email");
        final String phone = getIntent().getStringExtra("phone");

        tName=findViewById(R.id.editName);
        tEmail=findViewById(R.id.editEmail);
        tPhone=findViewById(R.id.editPhone);
        imageView=findViewById(R.id.editImage);
        edit=findViewById(R.id.edit);

        tName.setText(name);
        tEmail.setText(email);
        tPhone.setText(phone);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SplachScreenActivity.class);
                i.putExtra("value","editCustomer");
                i.putExtra("name",name);
                i.putExtra("editName",tName.getText().toString());
                i.putExtra("editEmail",tEmail.getText().toString());
                i.putExtra("editPhone",tPhone.getText().toString());
                startActivity(i);
                finish();
            }
        });
    }
}