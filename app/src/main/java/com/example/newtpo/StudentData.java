package com.example.newtpo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentData extends AppCompatActivity {



    RecyclerView recyclerView;

    ArrayList<Student> listItem;
    MyAdapter myAdapter;
    public String query=null;

    FloatingActionButton btn;
    FirebaseFirestore db;

    File filepath=new File(Environment.getExternalStorageDirectory()+"/Student.xls");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        recyclerView= findViewById(R.id.recyclerView);
        btn = findViewById(R.id.filterfloat);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentData.this, FilterStu.class);
                startActivityForResult(intent, 101);

            }
        });

//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listItem=new ArrayList<>();
//        myAdapter=new MyAdapter(listItem);
//        recyclerView.setAdapter(myAdapter);
        fetchData();

    }




//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listItem=new ArrayList<>();
//        myAdapter=new MyAdapter(listItem);
//        recyclerView.setAdapter(myAdapter);
//        fetchData();
//        Log.d("Tag","onStart  ");
//
//    }
//
//    @Override
//    protected void onResume() {
//          super.onResume();
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        listItem=new ArrayList<>();
////        myAdapter=new MyAdapter(listItem);
////        recyclerView.setAdapter(myAdapter);
////        fetchData();
//        Log.d("Tag","onResume  "+query);
//
//    }


//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        Log.d("Tag","onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d("Tag","onStop");
//    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        query=new String();
        if (requestCode == 101&&data!=null) {
            query = data.getStringExtra("data");
        }
        Log.d("Tag",query);
//
//        fetchData()
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listItem=new ArrayList<>();
//        myAdapter=new MyAdapter(listItem);
//        recyclerView.setAdapter(myAdapter);
        fetchData();
        Log.d("Tag",query);

    }

    protected void fetchData()
    {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItem=new ArrayList<>();
        myAdapter=new MyAdapter(listItem);
        recyclerView.setAdapter(myAdapter);
        Log.d("Tag","fetched");


        if(query==null)
        {
            Log.d("Tag","fetchedN");

            db= FirebaseFirestore.getInstance();
            db.collection("Students").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            Log.d("Tag","Inside");
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list)
                            {
                                Student obj=d.toObject(Student.class);
                                listItem.add(obj);
                                Log.d("Tag",obj.getFullName());
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        }
        Log.d("Tag","Mid");
        if(query!=null)
        {
            Log.d("Tag","fetchedS1");
            if (query.equals("IT"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereEqualTo("branch","Information Technology").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            if (query.equals("CSE"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereEqualTo("branch","Computer Science and Engineering").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


            if (query.equals("Mech"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereEqualTo("branch","Mechanical").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


            if (query.equals("EL"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereEqualTo("branch","Electrical").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }



            if (query.equals("EN"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereEqualTo("branch","Electronics").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


            if (query.equals("Civil"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereEqualTo("branch","Civil").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


            if (query.equals("CGPA >6.5"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereGreaterThan("cgpa","6.5").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            if (query.equals("CGPA >7"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereGreaterThan("cgpa","7").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            if (query.equals("CGPA >7.5"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereGreaterThan("cgpa","7.5").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }



            if (query.equals("CGPA >8"))
            {
                Log.d("Tag","fetchedS");

                db= FirebaseFirestore.getInstance();
                db.collection("Students").whereGreaterThan("cgpa","9").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Log.d("Tag","Inside");
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list)
                                {
                                    Student obj=d.toObject(Student.class);
                                    listItem.add(obj);
                                    Log.d("Tag",obj.getFullName());
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(StudentData.this,"Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }




        }
    }



    public void download_stu_data(View view){

        int arr_size=listItem.size();
        Log.d("Tag","Got it"+arr_size);


        //  ListIterator<Student> iterator=listItem.listIterator(listItem.size());

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Student Data");
        HSSFRow row=spreadsheet.createRow(1);
        HSSFCell cell=null;

        cell=row.createCell(1);
        cell.setCellValue("PRN Number");
        cell=row.createCell(2);
        cell.setCellValue("Full Name");
        cell=row.createCell(3);
        cell.setCellValue("Date of Birth");
        cell=row.createCell(4);
        cell.setCellValue("Age");
        cell=row.createCell(5);
        cell.setCellValue("Gender");
        cell=row.createCell(6);
        cell.setCellValue("College Email ID");
        cell=row.createCell(7);
        cell.setCellValue("Email ID (Alternate)");
        cell=row.createCell(8);
        cell.setCellValue("Mobile NO");
        cell=row.createCell(9);
        cell.setCellValue("Mobile NO (Alt)");
        cell=row.createCell(10);
        cell.setCellValue("LinkdIN ID");
        cell=row.createCell(11);
        cell.setCellValue("Branch");
        cell=row.createCell(12);
        cell.setCellValue("Year of Study");
        cell=row.createCell(13);
        cell.setCellValue("Graduation Year");
        cell=row.createCell(14);
        cell.setCellValue("SPI Sem 1");
        cell=row.createCell(15);
        cell.setCellValue("SPI Sem 2");
        cell=row.createCell(16);
        cell.setCellValue("SPI Sem 3");
        cell=row.createCell(17);
        cell.setCellValue("SPI Sem 4");
        cell=row.createCell(18);
        cell.setCellValue("SPI Sem 5");
        cell=row.createCell(19);
        cell.setCellValue("SPI Sem 6");
        cell=row.createCell(20);
        cell.setCellValue("CGPA");
        cell=row.createCell(21);
        cell.setCellValue("SSC Year");
        cell=row.createCell(22);
        cell.setCellValue("HSC/Diploma Year");
        cell=row.createCell(23);
        cell.setCellValue("SCC Percentage");
        cell=row.createCell(24);
        cell.setCellValue("HSC/Diploma Percentage");
        cell=row.createCell(25);
        cell.setCellValue("SCC School Name");
        cell=row.createCell(26);
        cell.setCellValue("HSC/Diploma College Name");
        cell=row.createCell(27);
        cell.setCellValue("Permenant Address");
        cell=row.createCell(28);
        cell.setCellValue("Residential Address");

        int i=1;

        for (int j=0;j<arr_size;j++)
        {
            i++;
            Student obj10=listItem.get(j);
            row=spreadsheet.createRow(i);

            cell=row.createCell(1);
            cell.setCellValue(obj10.getPrn());
            cell=row.createCell(2);
            cell.setCellValue(obj10.getFullName());
            cell=row.createCell(3);
            cell.setCellValue(obj10.getDob());
            cell=row.createCell(4);
            cell.setCellValue(obj10.getAge());
            cell=row.createCell(5);
            cell.setCellValue(obj10.getGender());
            cell=row.createCell(6);
            cell.setCellValue(obj10.getEmail());
            cell=row.createCell(7);
            cell.setCellValue(obj10.getAltEmail());
            cell=row.createCell(8);
            cell.setCellValue(obj10.getPhone());
            cell=row.createCell(9);
            cell.setCellValue(obj10.getPhone_alt());
            cell=row.createCell(10);
            cell.setCellValue(obj10.getLinkdin_id());
            cell=row.createCell(11);
            cell.setCellValue(obj10.getBranch());
            cell=row.createCell(12);
            cell.setCellValue(obj10.getCurrentYear());
            cell=row.createCell(13);
            cell.setCellValue(obj10.getPassingYear());
            cell=row.createCell(14);
            cell.setCellValue(obj10.getSpi_sem1());
            cell=row.createCell(15);
            cell.setCellValue(obj10.getSpi_sem2());
            cell=row.createCell(16);
            cell.setCellValue(obj10.getSpi_sem3());
            cell=row.createCell(17);
            cell.setCellValue(obj10.getSpi_sem4());
            cell=row.createCell(18);
            cell.setCellValue(obj10.getSpi_sem5());
            cell=row.createCell(19);
            cell.setCellValue(obj10.getSpi_sem6());
            cell=row.createCell(20);
            cell.setCellValue(obj10.getCgpa());
            cell=row.createCell(21);
            cell.setCellValue(obj10.getPassingyear10());
            cell=row.createCell(22);
            cell.setCellValue(obj10.getPassYear12_diploma());
            cell=row.createCell(23);
            cell.setCellValue(obj10.getPercent10());
            cell=row.createCell(24);
            cell.setCellValue(obj10.getPercent12());
            cell=row.createCell(25);
            cell.setCellValue(obj10.getSchoolName10());
            cell=row.createCell(26);
            cell.setCellValue(obj10.getCollgen12_diploma_Name());
            cell=row.createCell(27);
            cell.setCellValue(obj10.getpAddress());
            cell=row.createCell(28);
            cell.setCellValue(obj10.getrAddress());


        }

        Log.d("Tag","Filefos");


        try
        {
            Log.d("Tag","Downloaded OK12");
            if(!filepath.exists()){
                filepath.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(filepath);
            workbook.write(fileOutputStream);
            Log.d("Tag","Downloaded OK12");

            if(fileOutputStream!=null){
                Log.d("Tag","Downloaded OK");
                fileOutputStream.flush();
                fileOutputStream.close();
            }


        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Tag","Downloaded error"+e.getMessage());
        }







    }


}