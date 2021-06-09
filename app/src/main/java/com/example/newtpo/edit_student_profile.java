package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class edit_student_profile extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void generalDetails(View view)
    {
        startActivity(new Intent(getApplicationContext(),EditGeneralDetails.class));
    }
    public void academicDetails(View view)
    {
        startActivity(new Intent(getApplicationContext(),EditContactDetails.class));
    }
    public void contactDetails(View view)
    {
        startActivity(new Intent(getApplicationContext(),EditAcademicDetails.class));
    }
}