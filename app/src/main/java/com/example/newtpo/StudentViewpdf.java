package com.example.newtpo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class StudentViewpdf extends AppCompatActivity {
    private RecyclerView recview;
    private  Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_viewpdf);

        recview=(RecyclerView) findViewById(R.id.student_viewpdf);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<file> options=new FirebaseRecyclerOptions.Builder<file>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("mydocument"),file.class)
                .build();

        adapter=new Adapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}