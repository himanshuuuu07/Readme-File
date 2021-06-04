package com.example.newtpo;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Update_data extends AppCompatActivity {

    TextInputEditText filename;
    String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        filename=findViewById(R.id.file_name);

    }


    public void update(View view) throws IOException {
        String docID=new String();
        fileName="/";
        fileName= fileName+filename.getText().toString().trim();
        File filepath=new File(Environment.getExternalStorageDirectory()+fileName);
        FileInputStream inputStream=new FileInputStream(filepath);

        HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
        HSSFSheet sheet=workbook.getSheetAt(0);

        int rows=sheet.getLastRowNum();
        int cols=sheet.getRow(1).getLastCellNum();






    }

    public Student getDocument(String prn)
    {
        String documentId;
        ArrayList<Student> listItem=new ArrayList<>();
        FirebaseFirestore.getInstance().collection("Students").whereEqualTo("prn",prn).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                    Student obj=d.toObject(Student.class);
                    listItem.add(obj);
                    Log.d("Tag",obj.getFullName());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(Update_data.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Student stu=listItem.get(0);
        return stu;
    }



}