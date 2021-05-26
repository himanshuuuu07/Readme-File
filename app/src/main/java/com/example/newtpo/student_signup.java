package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class student_signup extends AppCompatActivity {

    TextInputEditText emailText,passwordText,confirm_passwordText;
    Button sign_up;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emailText=(TextInputEditText)findViewById(R.id.email_id);
        confirm_passwordText=(TextInputEditText)findViewById(R.id.confirm_password);
        passwordText=(TextInputEditText)findViewById(R.id.password);
        sign_up=(Button) findViewById(R.id.singup_btn);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar);

        fAuth=FirebaseAuth.getInstance();


        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),student_login.class));
            finish();
        }

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailText.getText().toString().trim();
                String password=passwordText.getText().toString().trim();
                String confirm_password=confirm_passwordText.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    emailText.setError("Email is required.");
                    emailText.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
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


                progressBar.setVisibility(View.VISIBLE);
                //register user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(student_signup.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),student_login.class));
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(student_signup.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}