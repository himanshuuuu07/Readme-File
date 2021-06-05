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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
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

        int rows=sheet.getPhysicalNumberOfRows();
        int cols=sheet.getRow(0).getLastCellNum();

        HSSFRow row1=sheet.getRow(0);


        Log.d("Tag","before for1");

        for(int r=1;r<rows;r++)
        {
            HSSFRow row2=sheet.getRow(r);
            Student student=null;

            Log.d("Tag","before for2");
            for(int c=1;c<cols;c++)
            {
                HSSFCell cell=row2.getCell(0);

                String prn=cell.getStringCellValue();
                student=getDocument(prn);

                if(student==null)
                {
                    break;
                }


                HSSFCell cell1=row2.getCell(c);

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM1")))
                {
                    student.setSpi_sem1(cell1.getStringCellValue());
                    continue;
                }

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM2")))
                {
                    student.setSpi_sem2(cell1.getStringCellValue());
                    continue;
                }

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM3")))
                {
                    student.setSpi_sem3(cell1.getStringCellValue());
                    continue;
                }

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM4")))
                {
                    student.setSpi_sem4(cell1.getStringCellValue());
                    continue;
                }

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM5")))
                {
                    student.setSpi_sem5(cell1.getStringCellValue());
                    continue;
                }

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("SPI_SEM6")))
                {
                    student.setSpi_sem6(cell1.getStringCellValue());
                    continue;
                }

                if(!(row1.getCell(c).getStringCellValue().equalsIgnoreCase("CGPA")))
                {
                    student.setCgpa(cell1.getStringCellValue());
                    continue;
                }



            }
            Log.d("Tag","After for2");
            setData(student);
            Log.d("Tag","Updated");

        }






        Log.d("Tag","Sucessfully updated");

    }

    public void setData(Student stu)
    {
        Log.d("Tag","IN setData");
        FirebaseFirestore.getInstance().collection("Students").document(stu.getUserID()).set(stu);
        Log.d("Tag","Sucessfully updated in set data");
    }

    public Student getDocument(String prn)
    {
        Log.d("Tag","In getDoc");
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
        Log.d("Tag","End of getDoc");
        return stu;
    }



}