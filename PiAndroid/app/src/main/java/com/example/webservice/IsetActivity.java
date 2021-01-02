package com.example.webservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class IsetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iset);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_iset,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewImages:
                Intent i1 = new Intent(getApplicationContext(),SlideImagesActivity.class);
                startActivity(i1);
                break;
            case R.id.products:
                Intent i2 = new Intent(getApplicationContext(),SplachScreenActivity.class);
                i2.putExtra("value","products");
                startActivity(i2);
                finish();
                break;
            case R.id.customers:
                Intent i3 = new Intent(getApplicationContext(),SplachScreenActivity.class);
                i3.putExtra("value","customers");
                startActivity(i3);
                finish();
                break;
            case R.id.map:
                Intent i4 = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i4);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}