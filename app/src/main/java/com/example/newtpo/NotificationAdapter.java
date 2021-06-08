package com.example.newtpo;

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

public class NotificationAdapter extends FirebaseRecyclerAdapter<Notification,NotificationAdapter.mynotiVeiwHolder>
{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotificationAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Notification> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull NotificationAdapter.mynotiVeiwHolder holder, int position, @NonNull @NotNull Notification model) {
        holder.heading.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.send.setText(model.getSendby());
        holder.date.setText(model.getTime());


    }

    @NonNull
    @NotNull
    @Override
    public mynotiVeiwHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout,parent,false);
        return new mynotiVeiwHolder(v);
    }

    public  class mynotiVeiwHolder extends RecyclerView.ViewHolder
    {
        TextView heading,desc,send,date;
        ImageView delete;
        public mynotiVeiwHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            heading=itemView.findViewById(R.id.notification_title);
            desc=itemView.findViewById(R.id.notification_description);
            send=itemView.findViewById(R.id.notification_sendby);
            date=itemView.findViewById(R.id.notification_time);
        }
    }
}