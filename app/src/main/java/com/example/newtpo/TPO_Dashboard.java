package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class TPO_Dashboard extends AppCompatActivity {

    private FirebaseAuth fAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_dashboard);
    }



    public void logout(View view){
        fAuth.signOut();
        Toast.makeText(TPO_Dashboard.this,"Logged out Sucessfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),student_login.class));
        finish();
    }


    public void records(View view){
        startActivity(new Intent(getApplicationContext(),StudentData.class));
    }

    public void records_tpo(View view) {
        startActivity(new Intent(getApplicationContext(),ViewFiles.class));
    }

    public void policy_tpo(View view) {
        startActivity(new Intent(getApplicationContext(),TpoPolicy.class));
    }
}