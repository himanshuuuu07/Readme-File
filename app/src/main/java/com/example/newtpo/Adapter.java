package com.example.newtpo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class Adapter extends FirebaseRecyclerAdapter<file,Adapter.myViewHolder>
{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull @NotNull FirebaseRecyclerOptions<file> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull Adapter.myViewHolder holder, int position, @NonNull @NotNull file model)
    {

        holder.title.setText(String.valueOf(model.getFileName()));
        holder.desc.setText(String.valueOf(model.getFileDes()));

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.img.getContext(),ViewPDF.class);
                intent.putExtra("filename",model.getFileName());
                intent.putExtra("fileurl",model.getFileUrl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img.getContext().startActivity(intent);
            }
        });


    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pdf_file,parent,false);
        return new Adapter.myViewHolder(v);
    }

    public  class myViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView title,desc;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.pdf_icon);
            title=itemView.findViewById(R.id.pdfHeading);
            desc=itemView.findViewById(R.id.pdfDescription);
        }
    }
}