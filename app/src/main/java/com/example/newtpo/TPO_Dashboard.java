package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TPO_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_dashboard);
    }



    public void records(View view){
        startActivity(new Intent(getApplicationContext(),StudentData.class));
    }
}