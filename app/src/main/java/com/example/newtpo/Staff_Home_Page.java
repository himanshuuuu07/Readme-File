package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Staff_Home_Page extends AppCompatActivity {

    private FirebaseAuth fAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home_page);
    }



    public void logout(View view){
        fAuth.signOut();
        Toast.makeText(Staff_Home_Page.this,"Logged out Sucessfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),student_login.class));
        finish();
    }


    public void stu_data(View view){
        startActivity(new Intent(getApplicationContext(),StudentData.class));
    }

    public void bulk_update(View view){
        startActivity(new Intent(getApplicationContext(),Update_data.class));
    }

    public void records_tpo(View view) {
        startActivity(new Intent(getApplicationContext(),ViewFiles.class));
    }

    public void notifications_tpo(View view) {
        startActivity(new Intent(getApplicationContext(),Notification_TPO.class));
    }

    public void staff_profile(View view) {
        startActivity(new Intent(getApplicationContext(),Staff_Profile.class));
    }
}