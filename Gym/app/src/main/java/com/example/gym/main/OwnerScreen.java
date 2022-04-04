package com.example.gym.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.R;
import com.example.gym.login.Login;

public class OwnerScreen extends AppCompatActivity {
    private TextView ownername;
    private TextView ownerloginid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownerscreen);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Login.username);
        ownername = findViewById(R.id.ownername);
        ownername.setText(name);
        String id = intent.getStringExtra(Login.loginid);
        ownerloginid = findViewById(R.id.ownerloginid);
        ownerloginid.setText(id);
    }
}
