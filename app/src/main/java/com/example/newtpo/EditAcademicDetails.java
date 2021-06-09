package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EditAcademicDetails extends AppCompatActivity
{
    AutoCompleteTextView branch,Yos,yog;
    TextInputEditText sscpy,sscnm,sscper,hscpy,hscnm,hscper;
    String spy,snm,sper,hpy,hnm,hper;
    Button update;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_academic_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        branch=findViewById(R.id.branch_edit);
        Yos=findViewById(R.id.year_of_study_edit);
        yog=findViewById(R.id.year_of_graduation_edit);
        sscpy=findViewById(R.id.year_of_ssc_edit);
        sscnm=findViewById(R.id.scc_school_name_edit);
        sscper=findViewById(R.id.percent_ssc_edit);
        hscpy=findViewById(R.id.year_of_hsc_diploma_edit);
        hscnm=findViewById(R.id.hsc_diploma_college_name_edit);
        hscper=findViewById(R.id.percent_hsc_diploma_edit);

        update=findViewById(R.id.update_academic_details);
        firebaseAuth=FirebaseAuth.getInstance();
        String uid=firebaseAuth.getCurrentUser().getUid();

        String[] items1=new String[]{"Computer Science and Engineering","Information Technology","Electronics","Electrical","Mechanical","Civil"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(EditAcademicDetails.this, R.layout.branch_item, items1);
        branch.setAdapter(adapter1);

        String[] items3=new String[]{"Second Year","Third Year","Fourth Year"};
        ArrayAdapter<String> adapter3=new ArrayAdapter<>(EditAcademicDetails.this, R.layout.branch_item, items3);
        Yos.setAdapter(adapter3);

        String[] items4=new String[]{"2021","2022","2023","2024"};
        ArrayAdapter<String> adapter4=new ArrayAdapter<>(EditAcademicDetails.this, R.layout.branch_item, items4);
        yog.setAdapter(adapter4);

        spy=sscpy.getText().toString();
        snm=sscnm.getText().toString();
        sper=sscper.getText().toString();
        hpy=hscpy.getText().toString();
        hnm=hscnm.getText().toString();
        hper= hscper.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                spy=sscpy.getText().toString();
                snm=sscnm.getText().toString();
                sper=sscper.getText().toString();
                hpy=hscpy.getText().toString();
                hnm=hscnm.getText().toString();
                hper= hscper.getText().toString();

                DocumentReference documentReference= FirebaseFirestore.getInstance()
                        .collection("Students")
                        .document(uid);
                Map<String,Object> map=new HashMap<>();
                map.put("schoolName10",snm);
                map.put("collegen12_diplomaName",hnm);
                map.put("passingyear10",spy);
                map.put("passingYear12_diploma",hpy);
                map.put("percent10",sper);
                map.put("percent12",hper);

                documentReference.update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditAcademicDetails.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),edit_student_profile.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(EditAcademicDetails.this,"Failed to Update"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}