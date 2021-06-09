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

public class Login_0 extends AppCompatActivity {


    TextInputEditText emailText,passwordText;
    Button sign_in;

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
        setContentView(R.layout.activity_login0);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        emailText=findViewById(R.id.email);
        passwordText=findViewById(R.id.password);
        sign_in=findViewById(R.id.sign_in);


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

                fAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {


                        emailText.setText("");
                        passwordText.setText("");
                        Toast.makeText(Login_0.this,"Loggedin Sucessfully",Toast.LENGTH_SHORT).show();
                        checkuserAccessLevel(authResult.getUser().getUid());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(Login_0.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });





    }


    public void btn_forgot(View view)
    {
        startActivity(new Intent(getApplicationContext(),Forgot_password.class));
        finish();
    }


    private void checkuserAccessLevel(String uid){
        DocumentReference df=fStore.collection("TPO").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: "+documentSnapshot.getData());
                if(documentSnapshot.getString("isAdmin")!=null){
                    startActivity(new Intent(getApplicationContext(),TPO_Dashboard.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),TPO_Dashboard.class));
            finish();
        }
    }
}