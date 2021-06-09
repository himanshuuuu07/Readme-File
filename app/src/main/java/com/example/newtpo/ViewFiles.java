package com.example.newtpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFiles extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private  Adapter adapter;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);
        btn=findViewById(R.id.uploadfile_pdf);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        recyclerView=(RecyclerView) findViewById(R.id.fileRecycler_pdf);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UploadFile_TPO.class));
            }
        });

        FirebaseRecyclerOptions<file> options=new FirebaseRecyclerOptions.Builder<file>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("mydocument"),file.class)
                .build();

        adapter=new Adapter(options);
        recyclerView.setAdapter(adapter);
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