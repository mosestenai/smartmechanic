package com.example.mechanic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button login,proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        proceed= findViewById(R.id.proceed);

        proceed.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent= new Intent(this,loginActivity.class);
            startActivity(intent);
        });
    }
}