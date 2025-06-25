package com.example.vt6002cem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadEventActivity extends AppCompatActivity {
    private RecyclerView rvEventList;
    private EventAdapter eventAdapter;
    private List<Events> eventList;
    private DatabaseReference databaseReference;
    private Button btnBack, btnDeleteEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_event);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> navigateToHome());

        btnDeleteEvents = findViewById(R.id.btnDeleteEvents);
        btnDeleteEvents.setOnClickListener(v -> startActivity(new Intent(this, DeleteEventActivity.class)));

        rvEventList = findViewById(R.id.rvEventList);
        rvEventList.setLayoutManager(new LinearLayoutManager(this));
        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList);
        rvEventList.setAdapter(eventAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvEventList.getContext(),
                LinearLayoutManager.VERTICAL);
        rvEventList.addItemDecoration(dividerItemDecoration);

        databaseReference = FirebaseDatabase.getInstance().getReference("Events");

        fetchEventData();
    }

    private void fetchEventData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Events event = snapshot.getValue(Events.class);
                    if (event != null) {
                        eventList.add(event);
                    }
                }
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ReadEventActivity", "Database error: " + databaseError.getMessage());
            }
        });
    }

    private void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}