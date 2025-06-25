package com.example.vt6002cem;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddEventActivity extends AppCompatActivity {
    private EditText etEventName;
    private EditText etEventLocation;
    private EditText etEventDate;
    private EditText etEventTime;
    private EditText etEventHolder;
    private EditText etDescription;
    private EditText etCapacity;
    private Button btnAddEvent;
    private Button btnAddBackToHomePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_event);

        etEventName = findViewById(R.id.etEventName);
        etEventLocation = findViewById(R.id.etEventLocation);
        etEventDate = findViewById(R.id.etEventDate);
        etEventTime = findViewById(R.id.etEventTime);
        etEventHolder = findViewById(R.id.etEventHolder);
        etDescription = findViewById(R.id.etDescription);
        etCapacity = findViewById(R.id.etCapacity);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        btnAddBackToHomePage = findViewById(R.id.btnAddBackToHomePage);
        btnAddBackToHomePage.setOnClickListener(v -> {
            navigateToHome();
        });

        btnAddEvent.setOnClickListener(v -> {
            String EventName = etEventName.getText().toString();
            String EventLocation = etEventLocation.getText().toString();
            String EventDate = etEventDate.getText().toString();
            String EventTime = etEventTime.getText().toString();
            String EventHolder = etEventHolder.getText().toString();
            String Description = etDescription.getText().toString();
            String Capacity = etCapacity.getText().toString();

            if (EventName.isEmpty()){
                etEventName.setError("cannot be empty");
                return;
            }
            if (EventLocation.isEmpty()){
                etEventLocation.setError("cannot be empty");
                return;
            }
            if (EventDate.isEmpty()){
                etEventDate.setError("cannot be empty");
                return;
            }
            if (EventTime.isEmpty()){
                etEventTime.setError("cannot be empty");
                return;
            }
            if (EventHolder.isEmpty()){
                etEventHolder.setError("cannot be empty");
                return;
            }
            if (Description.isEmpty()){
                etDescription.setError("cannot be empty");
                return;
            }
            if (Capacity.isEmpty()){
                etCapacity.setError("cannot be empty");
                return;
            }

            addEventToDB(EventName, EventLocation, EventDate, EventTime, EventHolder, Description, Capacity);

        });


    }

    private void addEventToDB(String EventName, String EventLocation, String EventDate,String EventTime,String EventHolder,String Description,String Capacity){
        HashMap<String, Object> addEventHashmap = new HashMap<>();
        addEventHashmap.put("EventName", EventName);
        addEventHashmap.put("EventLocation", EventLocation);
        addEventHashmap.put("EventDate", EventDate);
        addEventHashmap.put("EventTime", EventTime);
        addEventHashmap.put("EventHolder", EventHolder);
        addEventHashmap.put("Description", Description);
        addEventHashmap.put("Capacity", Capacity);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference EventsRef = database.getReference("Events");
        String key = EventsRef.push().getKey();
        addEventHashmap.put("key", key);
        EventsRef.child(key).setValue(addEventHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddEventActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                etEventName.getText().clear();
                etEventLocation.getText().clear();
                etEventDate.getText().clear();
                etEventTime.getText().clear();
                etEventHolder.getText().clear();
                etDescription.getText().clear();
                etCapacity.getText().clear();
                navigateToHome();
            }
        });
    }
    private void navigateToHome() {
        Log.d(TAG, "Navigating Back to Home");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}