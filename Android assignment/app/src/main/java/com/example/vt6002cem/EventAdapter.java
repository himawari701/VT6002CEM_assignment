package com.example.vt6002cem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventsViewHolder> {
    private List<Events> eventList;
    private DeleteEventActivity activity;

    public EventAdapter(List<Events> eventList) {
        this.eventList = eventList;
    }

    public EventAdapter(List<Events> eventList, DeleteEventActivity activity) {
        this.eventList = eventList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        Events event = eventList.get(position);
        holder.bind(event);

        // Handle click for deletion
        holder.itemView.setOnClickListener(v -> {
            if (activity != null) {
                activity.deleteEvent(event.getKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventsViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventName, tvEventLocation, tvEventDate, tvEventTime, tvEventHolder, tvDescription, tvCapacity;

        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvEventLocation = itemView.findViewById(R.id.tvEventLocation);
            tvEventDate = itemView.findViewById(R.id.tvEventDate);
            tvEventTime = itemView.findViewById(R.id.tvEventTime);
            tvEventHolder = itemView.findViewById(R.id.tvEventHolder);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCapacity = itemView.findViewById(R.id.tvCapacity);
        }

        public void bind(Events event) {
            tvEventName.setText(event.getEventName());
            tvEventLocation.setText(event.getEventLocation());
            tvEventDate.setText(event.getEventDate());
            tvEventTime.setText(event.getEventTime());
            tvEventHolder.setText(event.getEventHolder());
            tvDescription.setText(event.getDescription());
            tvCapacity.setText(event.getCapacity());
        }
    }
}