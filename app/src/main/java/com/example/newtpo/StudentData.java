package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StudentData extends AppCompatActivity {



    RecyclerView recyclerView;

    ArrayList<Student> listItem;
    MyAdapter myAdapter;
    public String query=null;

    FloatingActionButton btn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);


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


    protected void onStartCommand()
    {

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

}