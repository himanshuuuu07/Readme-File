package com.example.newtpo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class LogAdapter extends FirebaseRecyclerAdapter<Logs,LogAdapter.myLogVeiwHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public LogAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Logs> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull LogAdapter.myLogVeiwHolder holder, int position, @NonNull @NotNull Logs model) {
        holder.t1.setText(model.getUpdatedBy());
        holder.t2.setText(model.getTime());
    }

    @NonNull
    @NotNull
    @Override
    public myLogVeiwHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.log_layout,parent,false);
        return new myLogVeiwHolder(v);
    }

    public  class myLogVeiwHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2;
        public myLogVeiwHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            t1=itemView.findViewById(R.id.Log_updatedby);
            t2=itemView.findViewById(R.id.log_time);
        }
    }

}
