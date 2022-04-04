package com.example.gym.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.R;
import com.example.gym.login.Login;

public class UserScreen extends AppCompatActivity {
    private TextView uname;
    private TextView userloginid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userscreen);


        Intent intent = getIntent();
        String name = intent.getStringExtra(Login.username);
        uname = findViewById(R.id.uname);
        uname.setText(name);
        String id = intent.getStringExtra(Login.loginid);
        userloginid = findViewById(R.id.userloginid);
        userloginid.setText(id);
    }
}
