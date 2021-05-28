package com.example.newtpo;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<Student> listItems;
    private Context context;

    public MyAdapter(List<Student> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyAdapter.ViewHolder holder, int position)
    {
        Student data=listItems.get(position);
        holder.t1.setText(data.getFullName());
        holder.t2.setText(data.getPrn());
        holder.t3.setText(data.getBranch());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView t1,t2,t3;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            t1=(TextView)itemView.findViewById(R.id.t1);
            t2=(TextView)itemView.findViewById(R.id.t2);
            t3=(TextView)itemView.findViewById(R.id.t3);
        }
    }
}

