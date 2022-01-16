package com.example.mechanic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
Button mechanic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mechanic = findViewById(R.id.mechanic);

        mechanic.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this,AddmechanicActivity.class);
            startActivity(intent);
        });
    }
}