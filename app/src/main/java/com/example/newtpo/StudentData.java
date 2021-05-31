package com.example.newtpo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StudentData extends AppCompatActivity {


    AutoCompleteTextView stu_branch;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Student> listItem;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        stu_branch= findViewById(R.id.branch);
        String[] items1=new String[]{"Computer Science and Engineering","Information Technology","Electronics","Electrical","Mechanical","Civil"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(StudentData.this, R.layout.branch_item, items1);
        stu_branch.setAdapter(adapter1);
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItem=new ArrayList<>();
        adapter=new MyAdapter(listItem,this);
        recyclerView.setAdapter(adapter);

    }

    public final void fetchData(View v)
    {
        String branch=stu_branch.getText().toString();
        db= FirebaseFirestore.getInstance();
        db.collection("Students").whereEqualTo("branch",branch).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d("Tag","Inside");
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            Student obj=d.toObject(Student.class);
                            listItem.add(obj);
                        }
                        adapter.notifyDataSetChanged();
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