package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class EditGeneralDetails extends AppCompatActivity
{
    AutoCompleteTextView gender;
    TextInputEditText fname,age,dob,prn;
    String name,gen,ag,d,p;
    Button update;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_general_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gender=findViewById(R.id.gender_edit);
        fname=findViewById(R.id.full_name_edit);
        age=findViewById(R.id.age_edit);
        dob=findViewById(R.id.birth_date_edit);
        prn=findViewById(R.id.prn_no_edit);
        update=findViewById(R.id.update_general_details);

        firebaseAuth=FirebaseAuth.getInstance();
        String uid=firebaseAuth.getCurrentUser().getUid();

        String[] items2=new String[]{"Male","Female","Other"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(EditGeneralDetails.this, R.layout.branch_item, items2);
        gender.setAdapter(adapter2);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name=fname.getText().toString();
                ag=age.getText().toString();
                d=dob.getText().toString();
                p=prn.getText().toString();
                gen=gender.getText().toString();

                DocumentReference documentReference= FirebaseFirestore.getInstance()
                        .collection("Students")
                        .document(uid);
                Map<String,Object> map=new HashMap<>();
                map.put("fullName",name);
                map.put("age",ag);
                map.put("dob",d);
                map.put("prn",p);
                map.put("gender",gen);
                Log.d("TAG",name+" "+ag+" "+d);
                documentReference.update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditGeneralDetails.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),edit_student_profile.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(EditGeneralDetails.this,"Failed to Update"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}