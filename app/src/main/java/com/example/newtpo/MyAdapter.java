package com.example.newtpo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewholder>
{
    ArrayList<Student> listItems;
    //private Context context;

    public MyAdapter(ArrayList<Student> listItems) {
        this.listItems = listItems;
    }


//    public MyAdapter(List<Student> listItems, Context context) {
//        this.listItems = (ArrayList<Student>) listItems;
//        this.context = context;
//    }


    class myViewholder extends RecyclerView.ViewHolder
    {
        TextView t1, t2, t3;
        public myViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.t1);
            t2=(TextView)itemView.findViewById(R.id.t2);
            t3=(TextView)itemView.findViewById(R.id.t3);

        }
    }




    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
       return  new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.myViewholder holder, int position) {
        holder.t1.setText(listItems.get(position).getFullName());
        holder.t2.setText(listItems.get(position).getPrn());
        holder.t3.setText(listItems.get(position).getBranch());
    }



    @Override
    public int getItemCount() {
        return listItems.size();
    }


}

