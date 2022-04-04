package com.example.gym.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.R;
import com.example.gym.login.Login;

public class TrainerScreen extends AppCompatActivity {
    private TextView trainername;
    private TextView trainerloginid;

    private RecyclerView recyclerView;
    String [] arr = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainerscreen);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Login.username);
        trainername.setText(name);
        trainername = findViewById(R.id.trainername);
        String id = intent.getStringExtra(Login.loginid);
        trainerloginid = findViewById(R.id.trainerloginid);
        trainerloginid.setText(id);

        recyclerView = findViewById(R.id.trainerscreenrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
