package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class student_home_page extends AppCompatActivity {

    LinearLayout cstudent_profile;

    private FirebaseAuth fAuth=FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        cstudent_profile=(LinearLayout) findViewById(R.id.profile_student);


        cstudent_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),student_profile.class));
            }
        });


    }






    public void logout(View view){
        fAuth.signOut();
        Toast.makeText(student_home_page.this,"Logged out Sucessfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),student_login.class));
        finish();
    }

}