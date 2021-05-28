package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class student_signup_form extends AppCompatActivity {



    AutoCompleteTextView stu_branch;
    AutoCompleteTextView stu_gender,stu_year_ofStudy,stu_year_ofGraduation;
    TextInputEditText stu_name,stu_prn,stu_dob,stu_age,stu_college_email,stu_alt_email,stu_phone,stu_alt_phone,stu_linkdin,stu_per_address,stu_res_address,stu_ssc_passYear,stu_ssc_school,stu_ssc_percentage,stu_hscDiplo_passYear,stu_hscDiplo_college,stu_hscDiplo_percentage,stu_sem1_spi,stu_sem2_spi,stu_sem3_spi,stu_sem4_spi,stu_sem5_spi,stu_sem6_spi,stu_cgpa;
    Button update;

    private FirebaseAuth firebaseAuth;
    String userID;
    protected DocumentReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup_form);
     //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        firebaseAuth=FirebaseAuth.getInstance();
        userID=firebaseAuth.getCurrentUser().getUid();


        stu_branch= findViewById(R.id.branch);
        stu_gender= findViewById(R.id.gender);
        stu_year_ofStudy= findViewById(R.id.year_of_study);
        stu_year_ofGraduation= findViewById(R.id.year_of_graduation);

        stu_name= findViewById(R.id.full_name);
        stu_prn= findViewById(R.id.prn_no);
        stu_dob= findViewById(R.id.birth_date);
        stu_age= findViewById(R.id.age);
        stu_college_email= findViewById(R.id.email_college);
        stu_alt_email= findViewById(R.id.email_alt);
        stu_phone= findViewById(R.id.mobile);
        stu_alt_phone= findViewById(R.id.mobile_alt);
        stu_linkdin= findViewById(R.id.linkdin);
        stu_per_address= findViewById(R.id.address_per);
        stu_res_address= findViewById(R.id.address_res);
        stu_ssc_passYear= findViewById(R.id.year_of_ssc);
        stu_ssc_school= findViewById(R.id.scc_school_name);
        stu_ssc_percentage= findViewById(R.id.percent_ssc);
        stu_hscDiplo_passYear= findViewById(R.id.year_of_hsc_diploma);
        stu_hscDiplo_college= findViewById(R.id.hsc_diploma_college_name);
        stu_hscDiplo_percentage= findViewById(R.id.percent_hsc_diploma);
        stu_sem1_spi= findViewById(R.id.spi_sem1);
        stu_sem2_spi= findViewById(R.id.spi_sem2);
        stu_sem3_spi= findViewById(R.id.spi_sem3);
        stu_sem4_spi= findViewById(R.id.spi_sem4);
        stu_sem5_spi= findViewById(R.id.spi_sem5);
        stu_sem6_spi= findViewById(R.id.spi_sem6);
        stu_cgpa= findViewById(R.id.cgpa);

        update= findViewById(R.id.update_student_profile);



        String[] items4=new String[]{"2021","2022","2023","2024"};
        String[] items3=new String[]{"Second Year","Third Year","Fourth Year"};
        String[] items2=new String[]{"Male","Female","Other"};
        String[] items1=new String[]{"Computer Science and Engineering","Information Technology","Electronics","Electrical","Mechanical","Civil"};


        ArrayAdapter<String> adapter1=new ArrayAdapter<>(student_signup_form.this, R.layout.branch_item, items1);
        stu_branch.setAdapter(adapter1);

        ArrayAdapter<String> adapter2=new ArrayAdapter<>(student_signup_form.this, R.layout.branch_item, items2);
        stu_gender.setAdapter(adapter2);

        ArrayAdapter<String> adapter3=new ArrayAdapter<>(student_signup_form.this, R.layout.branch_item, items3);
        stu_year_ofStudy.setAdapter(adapter3);

        ArrayAdapter<String> adapter4=new ArrayAdapter<>(student_signup_form.this, R.layout.branch_item, items4);
        stu_year_ofGraduation.setAdapter(adapter4);




    }

    public void btn_create_student_profile(View view) {

        String fullName,prn,branch,email,altEmail,dob,phone,phone_alt,linkdin_id,gender,pAddress,rAddress,collgen12_diploma_Name,schoolName10,passingYear,currentYear,age,passYear12_diploma,passingyear10,percent12,percent10,spi_sem1,spi_sem2,spi_sem3,spi_sem4,spi_sem5,spi_sem6,cgpa;

        fullName=stu_name.getText().toString().trim();
        prn=stu_prn.getText().toString().trim();
        branch=stu_branch.getText().toString().trim();
        email=stu_college_email.getText().toString().trim();
        altEmail=stu_alt_email.getText().toString().trim();
        dob=stu_dob.getText().toString().trim();
        phone=stu_phone.getText().toString().substring(0,9);
        phone_alt=stu_alt_phone.getText().toString().substring(0,9);
        linkdin_id=stu_linkdin.getText().toString().trim();
        gender=stu_gender.getText().toString().trim();
        pAddress=stu_per_address.getText().toString().trim();
        rAddress=stu_res_address.getText().toString().trim();
        collgen12_diploma_Name=stu_hscDiplo_college.getText().toString().trim();
        schoolName10=stu_ssc_school.getText().toString().trim();
        passingYear=stu_year_ofGraduation.getText().toString().trim();
        currentYear=stu_year_ofStudy.getText().toString().trim();
        age=stu_age.getText().toString().trim();
        passYear12_diploma=stu_hscDiplo_passYear.getText().toString().trim();
        passingyear10=stu_ssc_passYear.getText().toString().trim();
        percent10=stu_ssc_percentage.getText().toString().trim();
        percent12=stu_hscDiplo_percentage.getText().toString().trim();
        spi_sem1=stu_sem1_spi.getText().toString().trim();
        spi_sem2=stu_sem2_spi.getText().toString().trim();
        spi_sem3=stu_sem3_spi.getText().toString().trim();
        spi_sem4=stu_sem4_spi.getText().toString().trim();
        spi_sem5=stu_sem5_spi.getText().toString().trim();
        spi_sem6=stu_sem6_spi.getText().toString().trim();
        cgpa=stu_cgpa.getText().toString().trim();

        String isUser="1";

        //String path="Students"+"BTech"+branch;
        dRef= FirebaseFirestore.getInstance().collection("Students").document(prn);
        Student stu=new Student(fullName,prn,branch,email,altEmail,dob,phone,phone_alt,linkdin_id,gender,pAddress,rAddress,collgen12_diploma_Name,schoolName10,passingYear,currentYear,age,passYear12_diploma,passingyear10,percent12,percent10,userID,spi_sem1,spi_sem2,spi_sem3,spi_sem4,spi_sem5,spi_sem6,cgpa,isUser);

        dRef.set(stu).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(student_signup_form.this,"Updated Sucessfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),student_login.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(student_signup_form.this,"Eroor"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}