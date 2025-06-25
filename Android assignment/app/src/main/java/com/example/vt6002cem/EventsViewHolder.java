package com.example.vt6002cem;

import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.view.View;

public class EventsViewHolder extends RecyclerView.ViewHolder {
    public TextView tvEventName;
    public TextView tvEventLocation;
    public TextView tvEventDate;
    public TextView tvEventTime;
    public TextView tvEventHolder;
    public TextView tvDescription;
    public TextView tvCapacity;
    public EventsViewHolder(@NonNull View itemView){
        super(itemView);
        tvEventName = itemView.findViewById(R.id.tvEventName);
        tvEventLocation = itemView.findViewById(R.id.tvEventLocation);
        tvEventDate = itemView.findViewById(R.id.tvEventDate);
        tvEventTime = itemView.findViewById(R.id.tvEventTime);
        tvEventHolder = itemView.findViewById(R.id.tvEventHolder);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvCapacity = itemView.findViewById(R.id.tvCapacity);
    }
}

