package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class student_profile extends AppCompatActivity {


    TextView stu_name,stu_prn,stu_dob,stu_age,stu_college_email,stu_alt_email,stu_phone,stu_alt_phone,stu_linkdin,stu_per_address,stu_res_address,stu_ssc_passYear,stu_ssc_school,stu_ssc_percentage,stu_hscDiplo_passYear,stu_hscDiplo_college,stu_hscDiplo_percentage,stu_sem1_spi,stu_sem2_spi,stu_sem3_spi,stu_sem4_spi,stu_sem5_spi,stu_sem6_spi,stu_cgpa,stu_branch,stu_gender,stu_year_ofStudy,stu_year_ofGraduation;
    private DocumentReference dRef;
    private String fullName,prn,branch,email,altEmail,dob,phone,phone_alt,linkdin_id,gender,pAddress,rAddress,collgen12_diploma_Name,schoolName10,passingYear,currentYear,age,passYear12_diploma,passingyear10,percent12,percent10,spi_sem1,spi_sem2,spi_sem3,spi_sem4,spi_sem5,spi_sem6,cgpa;
    student stu;


    @Override
    protected void onStart() {
        super.onStart();

        dRef= FirebaseFirestore.getInstance().document("Students");

        stu_branch=(TextView) findViewById(R.id.branch);
        stu_gender=(TextView) findViewById(R.id.gender);
        stu_year_ofStudy=(TextView) findViewById(R.id.year_of_study);
        stu_year_ofGraduation=(TextView) findViewById(R.id.year_of_graduation);

        stu_name=(TextView) findViewById(R.id.full_name);
        stu_prn=(TextView) findViewById(R.id.prn_no);
        stu_dob=(TextView) findViewById(R.id.birth_date);
        stu_age=(TextView) findViewById(R.id.age);
        stu_college_email=(TextView) findViewById(R.id.email_college);
        stu_alt_email=(TextView) findViewById(R.id.email_alt);
        stu_phone=(TextView) findViewById(R.id.mobile);
        stu_alt_phone=(TextView) findViewById(R.id.mobile_alt);
        stu_linkdin=(TextView) findViewById(R.id.linkdin);
        stu_per_address=(TextView) findViewById(R.id.address_per);
        stu_res_address=(TextView) findViewById(R.id.address_res);
        stu_ssc_passYear=(TextView) findViewById(R.id.year_of_ssc);
        stu_ssc_school=(TextView) findViewById(R.id.scc_school_name);
        stu_ssc_percentage=(TextView) findViewById(R.id.percent_ssc);
        stu_hscDiplo_passYear=(TextView) findViewById(R.id.year_of_hsc_diploma);
        stu_hscDiplo_college=(TextView) findViewById(R.id.hsc_diploma_college_name);
        stu_hscDiplo_percentage=(TextView) findViewById(R.id.percent_hsc_diploma);
        stu_sem1_spi=(TextView) findViewById(R.id.spi_sem1);
        stu_sem2_spi=(TextView) findViewById(R.id.spi_sem2);
        stu_sem3_spi=(TextView) findViewById(R.id.spi_sem3);
        stu_sem4_spi=(TextView) findViewById(R.id.spi_sem4);
        stu_sem5_spi=(TextView) findViewById(R.id.spi_sem5);
        stu_sem6_spi=(TextView) findViewById(R.id.spi_sem6);
        stu_cgpa=(TextView) findViewById(R.id.cgpa);



        dRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    stu=documentSnapshot.toObject(student.class);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Not Showing","Data",e);
            }
        });

        stu_name.setText(stu.getFullName());
        stu_prn.setText(stu.getPrn());
        stu_dob.setText(stu.getDob());
        stu_age.setText(stu.getAge());
        stu_gender.setText(stu.getGender());
        stu_college_email.setText(stu.getEmail());
        stu_alt_email.setText(stu.getAltEmail());
        stu_phone.setText(stu.getPhone());
        stu_alt_phone.setText(stu.getPhone_alt());
        stu_linkdin.setText(stu.getLinkdin_id());
        stu_per_address.setText(stu.getpAddress());
        stu_res_address.setText(stu.getrAddress());
        stu_branch.setText(stu.getBranch());
        stu_year_ofStudy.setText(stu.getCurrentYear());
        stu_year_ofGraduation.setText(stu.getPassingYear());
        stu_ssc_passYear.setText(stu.getPassingyear10());
        stu_ssc_school.setText(stu.getSchoolName10());
        stu_ssc_percentage.setText(stu.getPercent10());
        stu_hscDiplo_passYear.setText(stu.getPassYear12_diploma());
        stu_hscDiplo_college.setText((stu.getCollgen12_diploma_Name()));
        stu_hscDiplo_percentage.setText(stu.getPercent12());
        stu_sem1_spi.setText(stu.getSpi_sem1());
        stu_sem2_spi.setText(stu.getSpi_sem2());
        stu_sem3_spi.setText(stu.getSpi_sem3());
        stu_sem4_spi.setText(stu.getSpi_sem4());
        stu_sem5_spi.setText(stu.getSpi_sem5());
        stu_sem6_spi.setText(stu.getSpi_sem6());
        stu_cgpa.setText(stu.getCgpa());



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



//        dRef= FirebaseFirestore.getInstance().document("Students");
//
//        stu_branch=(TextView) findViewById(R.id.branch);
//        stu_gender=(TextView) findViewById(R.id.gender);
//        stu_year_ofStudy=(TextView) findViewById(R.id.year_of_study);
//        stu_year_ofGraduation=(TextView) findViewById(R.id.year_of_graduation);
//
//        stu_name=(TextView) findViewById(R.id.full_name);
//        stu_prn=(TextView) findViewById(R.id.prn_no);
//        stu_dob=(TextView) findViewById(R.id.birth_date);
//        stu_age=(TextView) findViewById(R.id.age);
//        stu_college_email=(TextView) findViewById(R.id.email_college);
//        stu_alt_email=(TextView) findViewById(R.id.email_alt);
//        stu_phone=(TextView) findViewById(R.id.mobile);
//        stu_alt_phone=(TextView) findViewById(R.id.mobile_alt);
//        stu_linkdin=(TextView) findViewById(R.id.linkdin);
//        stu_per_address=(TextView) findViewById(R.id.address_per);
//        stu_res_address=(TextView) findViewById(R.id.address_res);
//        stu_ssc_passYear=(TextView) findViewById(R.id.year_of_ssc);
//        stu_ssc_school=(TextView) findViewById(R.id.scc_school_name);
//        stu_ssc_percentage=(TextView) findViewById(R.id.percent_ssc);
//        stu_hscDiplo_passYear=(TextView) findViewById(R.id.year_of_hsc_diploma);
//        stu_hscDiplo_college=(TextView) findViewById(R.id.hsc_diploma_college_name);
//        stu_hscDiplo_percentage=(TextView) findViewById(R.id.percent_hsc_diploma);
//        stu_sem1_spi=(TextView) findViewById(R.id.spi_sem1);
//        stu_sem2_spi=(TextView) findViewById(R.id.spi_sem2);
//        stu_sem3_spi=(TextView) findViewById(R.id.spi_sem3);
//        stu_sem4_spi=(TextView) findViewById(R.id.spi_sem4);
//        stu_sem5_spi=(TextView) findViewById(R.id.spi_sem5);
//        stu_sem6_spi=(TextView) findViewById(R.id.spi_sem6);
//        stu_cgpa=(TextView) findViewById(R.id.cgpa);
//
//
//
//        dRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists()){
//                    stu=documentSnapshot.toObject(student.class);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull  Exception e) {
//                Log.w("Not Showing","Data",e);
//            }
//        });
//
//        stu_name.setText(stu.getFullName());
//        stu_prn.setText(stu.getPrn());
//        stu_dob.setText(stu.getDob());
//        stu_age.setText(stu.getAge());
//        stu_gender.setText(stu.getGender());
//        stu_college_email.setText(stu.getEmail());
//        stu_alt_email.setText(stu.getAltEmail());
//        stu_phone.setText(stu.getPhone());
//        stu_alt_phone.setText(stu.getPhone_alt());
//        stu_linkdin.setText(stu.getLinkdin_id());
//        stu_per_address.setText(stu.getpAddress());
//        stu_res_address.setText(stu.getrAddress());
//        stu_branch.setText(stu.getBranch());
//        stu_year_ofStudy.setText(stu.getCurrentYear());
//        stu_year_ofGraduation.setText(stu.getPassingYear());
//        stu_ssc_passYear.setText(stu.getPassingyear10());
//        stu_ssc_school.setText(stu.getSchoolName10());
//        stu_ssc_percentage.setText(stu.getPercent10());
//        stu_hscDiplo_passYear.setText(stu.getPassYear12_diploma());
//        stu_hscDiplo_college.setText((stu.getCollgen12_diploma_Name()));
//        stu_hscDiplo_percentage.setText(stu.getPercent12());
//        stu_sem1_spi.setText(stu.getSpi_sem1());
//        stu_sem2_spi.setText(stu.getSpi_sem2());
//        stu_sem3_spi.setText(stu.getSpi_sem3());
//        stu_sem4_spi.setText(stu.getSpi_sem4());
//        stu_sem5_spi.setText(stu.getSpi_sem5());
//        stu_sem6_spi.setText(stu.getSpi_sem6());
//        stu_cgpa.setText(stu.getCgpa());


    }

    public void btn_home (View view)
    {
        startActivity(new Intent(getApplicationContext(),student_home_page.class));
    }

//    public void btn_edit (View view)
//    {
//        startActivity(new Intent(getApplicationContext(),edit_student_profile.class));
//    }


}