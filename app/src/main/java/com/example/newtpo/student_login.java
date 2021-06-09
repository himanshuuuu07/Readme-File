package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class student_login extends AppCompatActivity {

    TextInputEditText emailText,passwordText;
    Button sign_up,sign_in,forgot_pass;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    private static final String coDomain = "walchandsangli.ac.in";
    private static final String EMAIL_PATTERN1 =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + Pattern.quote(coDomain) + "$";

    private static final Pattern EMAIL_ADDRESS2=Pattern.compile(EMAIL_PATTERN1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();


        emailText= findViewById(R.id.email_id);
        passwordText= findViewById(R.id.password);
        sign_up= findViewById(R.id.singup_btn);
        sign_in= findViewById(R.id.singin_btn);
        forgot_pass=findViewById(R.id.forgot_password);





        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=emailText.getText().toString().trim();
                String password=passwordText.getText().toString().trim();

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


                //login user in firebase

                fAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        emailText.setText("");
                        passwordText.setText("");
                        Toast.makeText(student_login.this,"Loggedin Sucessfully",Toast.LENGTH_SHORT).show();
                        checkuserAccessLevel(authResult.getUser().getUid());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(student_login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });



//
//
//            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        //if(fAuth.getCurrentUser().isEmailVerified())
//                        {
//
//                            emailText.setText("");
//                            passwordText.setText("");
//
//                            Toast.makeText(student_login.this,"Logged in Sucessfully",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),student_home_page.class));
//                            finish();
//                        }
//                      //  else
////                        {
////                            Toast.makeText(student_login.this,"Email is not verified",Toast.LENGTH_SHORT).show();
////                        }
//
//                    }
//                    else{
//
//                        Toast.makeText(student_login.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            });
//        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),student_signup.class));
                finish();
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Forgot_password.class));
                finish();
            }
        });

    }

//    public void btn_signup(View Object)
//    {
//        startActivity(new Intent(getApplicationContext(),student_signup.class));
//        finish();
//    }


    private void checkuserAccessLevel(String uid){
        DocumentReference df=fStore.collection("Students").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: "+documentSnapshot.getData());
                if(documentSnapshot.getString("isUser")!=null){
                    startActivity(new Intent(getApplicationContext(),student_home_page.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),student_home_page.class));
            finish();
        }
    }
}