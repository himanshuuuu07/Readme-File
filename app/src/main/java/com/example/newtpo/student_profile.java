package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class student_profile extends AppCompatActivity
{

    TextView fName,prn,dob,age,gender,email,altEmail,mobileNumber,altMobileNumber,linkedIn,pAddress,rAddress;
    TextView branch,yos,gy,s1,s2,s3,s4,s5,s6,cgpa;
    TextView ssName,hsName,spyear,hpyear,sscore,hscore;
    FirebaseAuth fAuth;
    DocumentReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        fName=findViewById(R.id.stuName);
        prn=findViewById(R.id.stuPRN);
        dob=findViewById(R.id.stuDOB);
        age=findViewById(R.id.stuAge);
        gender=findViewById(R.id.stuGender);
        email=findViewById(R.id.stuEmail);
        altEmail=findViewById(R.id.stuAltEmail);
        mobileNumber=findViewById(R.id.stuMobile);
        altMobileNumber=findViewById(R.id.stuAltMobile);
        linkedIn=findViewById(R.id.stuLinkedIN);
        pAddress=findViewById(R.id.stuPermanentAddress);
        rAddress=findViewById(R.id.stuResidentialAddress);
        branch=findViewById(R.id.stuBranch);
        yos=findViewById(R.id.stuCurrentYear);
        gy=findViewById(R.id.stuPassYear);
        s1=findViewById(R.id.stuSPI1);
        s2=findViewById(R.id.stuSPI2);
        s3=findViewById(R.id.stuSPI3);
        s4=findViewById(R.id.stuSPI4);
        s5=findViewById(R.id.stuSPI5);
        s6=findViewById(R.id.stuSPI6);
        cgpa=findViewById(R.id.stuCGPA);
        ssName=findViewById(R.id.stuSscName);
        spyear=findViewById(R.id.stuSSCPassYear);
        sscore=findViewById(R.id.stuSscScore);
        hsName=findViewById(R.id.stuHscName);
        hpyear=findViewById(R.id.stuHsCPassYear);
        hscore=findViewById(R.id.stuHscScore);



        fAuth=FirebaseAuth.getInstance();
        FirebaseUser user=fAuth.getCurrentUser();

        dr=FirebaseFirestore.getInstance().collection("Students").document(user.getUid());
        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {

                    fName.setText(documentSnapshot.getString("fullName"));
                    prn.setText(documentSnapshot.getString("prn"));
                    dob.setText(documentSnapshot.getString("dob"));
                    age.setText(documentSnapshot.getString("age"));
                    gender.setText(documentSnapshot.getString("gender"));
                    email.setText(documentSnapshot.getString("email"));
                    altEmail.setText(documentSnapshot.getString("altEmail"));
                    mobileNumber.setText(documentSnapshot.getString("phone"));
                    altMobileNumber.setText(documentSnapshot.getString("phone_alt"));
                    linkedIn.setText(documentSnapshot.getString("linkdin_id"));
                    pAddress.setText(documentSnapshot.getString("pAddress"));
                    rAddress.setText(documentSnapshot.getString("rAddress"));
                    branch.setText(documentSnapshot.getString("branch"));
                    yos.setText(documentSnapshot.getString("currentYear"));
                    gy.setText(documentSnapshot.getString("passingYear"));
                    s1.setText(documentSnapshot.getString("spi_sem1"));
                    s2.setText(documentSnapshot.getString("spi_sem2"));
                    s3.setText(documentSnapshot.getString("spi_sem3"));
                    s4.setText(documentSnapshot.getString("spi_sem4"));
                    s5.setText(documentSnapshot.getString("spi_sem5"));
                    s6.setText(documentSnapshot.getString("spi_sem6"));
                    cgpa.setText(documentSnapshot.getString("cgpa"));
                    ssName.setText(documentSnapshot.getString("schoolName10"));
                    spyear.setText(documentSnapshot.getString("passingyear10"));
                    sscore.setText(documentSnapshot.getString("percent10"));
                    hsName.setText(documentSnapshot.getString("collgen12_diploma_Name"));
                    hpyear.setText(documentSnapshot.getString("passYear12_diploma"));
                    hscore.setText(documentSnapshot.getString("percent12"));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Document not found",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void edit_stu_profile(View view){
        startActivity(new Intent(getApplicationContext(),edit_student_profile.class));
    }

}