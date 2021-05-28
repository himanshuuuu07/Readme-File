package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class student_signup extends AppCompatActivity {

    TextInputEditText emailText,passwordText,confirm_passwordText;
    Button sign_up;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

    private static final String coDomain = "walchandsangli.ac.in";
    private static final String EMAIL_PATTERN1 =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + Pattern.quote(coDomain) + "$";

    private static final Pattern EMAIL_ADDRESS2=Pattern.compile(EMAIL_PATTERN1);


    AutoCompleteTextView stu_branch;
    AutoCompleteTextView stu_gender,stu_year_ofStudy,stu_year_ofGraduation;
    TextInputEditText stu_name,stu_prn,stu_dob,stu_age,stu_college_email,stu_alt_email,stu_phone,stu_alt_phone,stu_linkdin,stu_per_address,stu_res_address,stu_ssc_passYear,stu_ssc_school,stu_ssc_percentage,stu_hscDiplo_passYear,stu_hscDiplo_college,stu_hscDiplo_percentage,stu_sem1_spi,stu_sem2_spi,stu_sem3_spi,stu_sem4_spi,stu_sem5_spi,stu_sem6_spi,stu_cgpa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        emailText=findViewById(R.id.email_id);
        confirm_passwordText=findViewById(R.id.confirm_password);
        passwordText=findViewById(R.id.password);
        sign_up=findViewById(R.id.singup_btn);



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





        String[] items4=new String[]{"2021","2022","2023","2024"};
        String[] items3=new String[]{"Second Year","Third Year","Fourth Year"};
        String[] items2=new String[]{"Male","Female","Other"};
        String[] items1=new String[]{"Computer Science and Engineering","Information Technology","Electronics","Electrical","Mechanical","Civil"};

        ArrayAdapter<String> adapter1=new ArrayAdapter<>(student_signup.this, R.layout.branch_item, items1);
        stu_branch.setAdapter(adapter1);

        ArrayAdapter<String> adapter2=new ArrayAdapter<>(student_signup.this, R.layout.branch_item, items2);
        stu_gender.setAdapter(adapter2);

        ArrayAdapter<String> adapter3=new ArrayAdapter<>(student_signup.this, R.layout.branch_item, items3);
        stu_year_ofStudy.setAdapter(adapter3);

        ArrayAdapter<String> adapter4=new ArrayAdapter<>(student_signup.this, R.layout.branch_item, items4);
        stu_year_ofGraduation.setAdapter(adapter4);





        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailText.getText().toString().trim();
                String password=passwordText.getText().toString().trim();
                String confirm_password=confirm_passwordText.getText().toString().trim();


                String fullName,prn,branch,email_c,altEmail,dob,phone,phone_alt,linkdin_id,gender,pAddress,rAddress,collgen12_diploma_Name,schoolName10,passingYear,currentYear,age,passYear12_diploma,passingyear10,percent12,percent10,spi_sem1,spi_sem2,spi_sem3,spi_sem4,spi_sem5,spi_sem6,cgpa;

                fullName=stu_name.getText().toString().trim();
                prn=stu_prn.getText().toString().trim();
                branch=stu_branch.getText().toString().trim();
                email_c=stu_college_email.getText().toString().trim();
                altEmail=stu_alt_email.getText().toString().trim();
                dob=stu_dob.getText().toString().trim();
                phone=stu_phone.getText().toString().substring(0,10);
                phone_alt=stu_alt_phone.getText().toString().substring(0,10);
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




                if(TextUtils.isEmpty(email))
                {
                    emailText.setError("Email is required.");
                    emailText.requestFocus();
                    return;
                }

                if(!EMAIL_ADDRESS2.matcher(email).matches())
                {
                    emailText.setError("Provide valid email.");
                    emailText.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    passwordText.setError("Password is required.");
                    passwordText.requestFocus();
                    return;
                }

                if(password.length()<6)
                {
                    passwordText.setError("Password must be >= 6 Characters");
                }

                if(!confirm_password.equals(password))
                {
                    confirm_passwordText.setError("Password not matching.");
                    confirm_passwordText.requestFocus();
                    return;
                }



                //register user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user=fAuth.getCurrentUser();

                        String isUser="1";
                        Student stu=new Student(fullName,prn,branch,email_c,altEmail,dob,phone,phone_alt,linkdin_id,gender,pAddress,rAddress,collgen12_diploma_Name,schoolName10,passingYear,currentYear,age,passYear12_diploma,passingyear10,percent12,percent10,user.getUid(),spi_sem1,spi_sem2,spi_sem3,spi_sem4,spi_sem5,spi_sem6,cgpa,isUser);

                        Toast.makeText(student_signup.this,"User Created",Toast.LENGTH_SHORT).show();

                        DocumentReference df=fStore.collection("Students").document(user.getUid());
                        df.set(stu);


                        startActivity(new Intent(getApplicationContext(),student_login.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(student_signup.this,"Error !"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });






//                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull  Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            emailText.setText("");
//                            passwordText.setText("");
//                            confirm_passwordText.setText("");
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(student_signup.this,"User Created",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),student_signup_form.class));
//                            finish();
//                        }
//                        else{
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(student_signup.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
            }
        });
    }
}