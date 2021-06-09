package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

public class EditContactDetails extends AppCompatActivity
{
    TextInputEditText email,altEmail,mobile,altMobile,pAddress,rAddress;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    Button update;
    String mail,amail,mob,amob,padd,radd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firestore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();
        String uid=fAuth.getCurrentUser().getUid();

        email=findViewById(R.id.pdfTitle);
        altEmail=findViewById(R.id.Emobile_alt);
        mobile=findViewById(R.id.Emobile);
        altMobile=findViewById(R.id.Emobile_alt);
        pAddress=findViewById(R.id.Eaddress_per);
        rAddress=findViewById(R.id.Eaddress_res);
        update=findViewById(R.id.EditgenDetails);

        mail=email.getText().toString();
        amail=altEmail.getText().toString();
        mob=mobile.getText().toString();
        amob=altMobile.getText().toString();
        padd=pAddress.getText().toString();
        radd=rAddress.getText().toString();

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                mail=email.getText().toString();
                amail=altEmail.getText().toString();
                mob=mobile.getText().toString();
                amob=altMobile.getText().toString();
                padd=pAddress.getText().toString();
                radd=rAddress.getText().toString();

                DocumentReference documentReference=FirebaseFirestore.getInstance()
                        .collection("Students")
                        .document(uid);
                Map<String,Object> map=new HashMap<>();
                map.put("altEmail",amail);
                map.put("email",mail);
                map.put("phone",mob);
                map.put("phone_alt",amob);
                map.put("pAddress",padd);
                map.put("rAddress",radd);

                documentReference.update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused)
                            {
                                Toast.makeText(EditContactDetails.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),edit_student_profile.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e)
                            {
                                Toast.makeText(EditContactDetails.this,"Failed to Update"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

}