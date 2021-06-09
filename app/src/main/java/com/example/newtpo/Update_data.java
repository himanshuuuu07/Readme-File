package com.example.newtpo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Update_data extends AppCompatActivity {

    TextInputEditText filename;
    String fileName;
    FirebaseFirestore db;
    ArrayList<Student> listItem=new ArrayList<Student>();
    Button bulkupdate,logs;
    Student student=new Student();
    DatabaseReference dbr;
    String userName,time,uid;
    Calendar currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        filename=findViewById(R.id.file_name);
        bulkupdate=findViewById(R.id.bulk_update_data);
        logs=findViewById(R.id.logsButton);
        db= FirebaseFirestore.getInstance();
        dbr= FirebaseDatabase.getInstance().getReference("Logs");
        currentTime = Calendar.getInstance();
        time=currentTime.getTime().toLocaleString();
        uid= FirebaseAuth.getInstance().getUid();
        db.collection("TPO").document(uid).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        userName=documentSnapshot.getString("Name");
                    }
                });

        bulkupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(Update_data.this,"Updating...!!!",Toast.LENGTH_SHORT).show();
                getDocument();
                Toast.makeText(Update_data.this,"Updated Successfully",Toast.LENGTH_SHORT).show();

                Logs obj=new Logs(userName,time);
                dbr.child(dbr.push().getKey()).setValue(obj)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                            }
                        });
            }
        });

        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),View_Logs.class));
            }
        });


    }




    public void setData(Student stu)
    {
        FirebaseFirestore.getInstance()
                .collection("Students")
                .document(stu.getUserID()).set(stu);
    }

    protected void getDocument()
    {
        listItem=new ArrayList<Student>();

        db.collection("Students").get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            Student obj=d.toObject(Student.class);
                            listItem.add(obj);
                        }

                        updateData(listItem);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.d("Tag","Inside failure");
                    }
                });
    }

    private void updateData(ArrayList<Student> listItem)
    {
        int listCnt=listItem.size();
        fileName="/";
        fileName= fileName+filename.getText().toString().trim();
        File filepath=new File(Environment.getExternalStorageDirectory()+fileName);
        FileInputStream inputStream= null;
        try {
            inputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook= null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet=workbook.getSheetAt(0);

        int rows=sheet.getPhysicalNumberOfRows();
        int cols=sheet.getRow(0).getLastCellNum();

        HSSFRow row1=sheet.getRow(0);


        for(int r=1;r<rows;r++)
        {

            HSSFRow row2=sheet.getRow(r);

            for(int c=1;c<cols;c++)
            {
                HSSFCell cell=row2.getCell(0);

                String prn=cell.getStringCellValue();


                int x;
                for(x=0;x<listCnt;x++)
                {
                    student=listItem.get(x);
                    if(student.getPrn().equals(prn))
                    {
                        break;
                    }
                }

                if(x==listCnt)
                {
                    break;
                }


                HSSFCell cell1=row2.getCell(c);
                DataFormatter formatter = new DataFormatter();
                String strValue = formatter.formatCellValue(cell1);

                if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM1")))
                {
                    student.setSpi_sem1(strValue);
                }
                else if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM2")))
                {
                    student.setSpi_sem2(strValue);
                }
                else if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM3")))
                {
                    student.setSpi_sem3(strValue);
                }
                else if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM4")))
                {
                    student.setSpi_sem4(strValue);
                }
                else if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM5")))
                {
                    student.setSpi_sem5(strValue);
                }

                else if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM6")))
                {
                    student.setSpi_sem6(strValue);
                }

                else if((row1.getCell(c).getStringCellValue().equalsIgnoreCase("CGPA")))
                {
                    student.setCgpa(strValue);
                }



            }

            setData(student);

        }

        Toast.makeText(Update_data.this,"Sucessfully Updated",Toast.LENGTH_SHORT).show();
    }


}