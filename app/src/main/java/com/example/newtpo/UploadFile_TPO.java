package com.example.newtpo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.NotNull;

public class UploadFile_TPO extends AppCompatActivity
{

    TextInputEditText title,description;
    Uri filePath;
    Button upload,browse,cancel;
    StorageReference storageReference;
    DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file_tpo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        storageReference= FirebaseStorage.getInstance().getReference();
        dbr= FirebaseDatabase.getInstance().getReference("mydocument");
        title=findViewById(R.id.pdfTitle);
        description=findViewById(R.id.pdfDes);
        upload=findViewById(R.id.uploadpdf);
        cancel=findViewById(R.id.cancel);
        browse=findViewById(R.id.browsepdf);
        upload.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                browse.setVisibility(View.VISIBLE);
            }
        });


        browse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent=new Intent();
                                intent.setType("application/pdf");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"Select File"),101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile(filePath);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==101)&&(resultCode==RESULT_OK))
        {
            filePath=data.getData();
            upload.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            browse.setVisibility(View.INVISIBLE);
        }
    }

    public void uploadfile(Uri path)
    {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("File is Uploading....!!!");
        pd.show();

        final StorageReference reference=storageReference.child("placementDetails/"+System.currentTimeMillis()+".pdf");
        reference.putFile(path)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                file obj=new file(title.getText().toString(),uri.toString(),description.getText().toString());
                                dbr.child(dbr.push().getKey()).setValue(obj);

                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();

                                upload.setVisibility(View.INVISIBLE);
                                cancel.setVisibility(View.INVISIBLE);
                                browse.setVisibility(View.VISIBLE);
                                title.setText("");
                                description.setText("");
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
                        float percent=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        pd.setMessage("Uploaded:"+(int)percent+"%");
                    }
                });
    }
}