package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class Notification_TPO extends AppCompatActivity
{
    DatabaseReference db;
    TextInputEditText title,description;
    String send="Message By: TPO";
    Button push,view_notification;
    Calendar currentTime;

    String theDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_tpo);

        db= FirebaseDatabase.getInstance().getReference("notification");
        title=findViewById(R.id.notification_title_get);
        description=findViewById(R.id.notification_description_get);
        push=findViewById(R.id.pushNotification_button);
        view_notification=findViewById(R.id.view_notification);
        currentTime = Calendar.getInstance();
        theDate=currentTime.getTime().toLocaleString();


        view_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewNotification.class));
            }
        });



        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification obj=new Notification(title.getText().toString(),description.getText().toString(),send,theDate);
                db.child(db.push().getKey()).setValue(obj)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Notification_TPO.this,"Notified",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });

//                addNotification();
            }
        });


//    }
//
//    private void addNotification() {
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.bellnotification)
//                        .setContentTitle("Notification")
//                        .setContentText(title.getText().toString());
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
    }
}