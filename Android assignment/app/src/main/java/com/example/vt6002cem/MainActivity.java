package com.example.vt6002cem;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLogout;
    private Button btnRead;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        // Check if user is logged in
        boolean isLoggedIn = getSharedPreferences("AuthPrefs", MODE_PRIVATE).getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            navigateToLogin();
        }

        // Set up the logout button
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Logout Button Clicked", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            clearSessionStatus();
            navigateToLogin();
        });

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(v ->{
            navigateToRead();
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v ->{
            navigateToAdd();
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            navigateToDelete();
        });
        
        Button btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(v ->{
            navigateToMap();
        });
        Button btnGoToCamera = findViewById(R.id.btnGoToCamera);
        btnGoToCamera.setOnClickListener(v ->{
            navigateToCamera();
        });


    }

    private void navigateToLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(loginIntent);
        finish();
    }
    private void navigateToRead(){
        Intent readIntent = new Intent(this, ReadEventActivity.class);
        startActivity(readIntent);
        finish();
    }
    private void navigateToAdd(){
        Intent addIntent = new Intent(this, AddEventActivity.class);
        startActivity(addIntent);
        finish();
    }
    private void navigateToDelete(){
        Intent deleteIntent = new Intent(this, DeleteEventActivity.class);
        startActivity(deleteIntent);
        finish();
    }
    private void navigateToMap(){
        Intent mapIntent = new Intent(this, MapActivity.class);
        startActivity(mapIntent);
        finish();
    }
    private void navigateToCamera(){
        Intent cameraIntent = new Intent(this, CameraActivity.class);
        startActivity(cameraIntent);
        finish();
    }

    private void clearSessionStatus() {
        getSharedPreferences("AuthPrefs", MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

}