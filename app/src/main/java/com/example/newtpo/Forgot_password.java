package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class Forgot_password extends AppCompatActivity {

    TextInputEditText emailText;
    Button send;
    private   FirebaseAuth firebaseAuth;

    private static final String coDomain = "walchandsangli.ac.in";
    private static final String EMAIL_PATTERN1 =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + Pattern.quote(coDomain) + "$";

    private static final Pattern EMAIL_ADDRESS2=Pattern.compile(EMAIL_PATTERN1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firebaseAuth=FirebaseAuth.getInstance();
        emailText= findViewById(R.id.email_id);
        send= findViewById(R.id.send_btn);

       send.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View v) {


               String email=emailText.getText().toString().trim();

               if(TextUtils.isEmpty(email)&&(!EMAIL_ADDRESS2.matcher(email).matches()))
               {
                   emailText.setError("Email is required.");
                   emailText.requestFocus();
                   return;
               }

               else {
                   firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull @NotNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(Forgot_password.this, "Link send to your registered email", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(),student_login.class));
                               finish();

                           }
                           else {
                               Toast.makeText(Forgot_password.this, "Error\n" + task.getException(), Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

               }

           }
       });


    }
}

