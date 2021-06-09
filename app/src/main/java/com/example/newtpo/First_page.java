package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class First_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

        public void btn_training_and_placement_officer(View Object)
        {
            startActivity(new Intent(getApplicationContext(),Login_0.class));
            finish();
        }

        public void btn_staff (View view)
        {
            startActivity(new Intent(getApplicationContext(),Staff_Login.class));
            finish();
        }
        public void btn_student (View view)
        {
            startActivity(new Intent(getApplicationContext(),student_login.class));
            finish();
        }

}