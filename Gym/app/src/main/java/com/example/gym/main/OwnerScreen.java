package com.example.gym.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gym.R;
import com.example.gym.login.Login;
import com.example.gym.owner.CreateGym;
import com.example.gym.owner.UpdateOwnerProfile;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class OwnerScreen extends AppCompatActivity {
    private TextView ownername;
    private TextView ownerloginid;

    private String name;
    private String id;

    private RecyclerView ownerrecyclerView;
    String [] arr = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownerscreen);

        Intent intent = getIntent();
        name = intent.getStringExtra(Login.username);
        ownername = findViewById(R.id.ownername);
        ownername.setText(name);

        id = intent.getStringExtra(Login.loginid);
        ownerloginid = findViewById(R.id.ownerloginid);
        ownerloginid.setText(id);

        ownerrecyclerView = findViewById(R.id.ownerscreenrecyclerview);
        ownerrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OwnerAdapter ownerAdapter = new OwnerAdapter(arr);
        ownerrecyclerView.setAdapter(ownerAdapter);
    }

    public void goto_ownerupdateprofile(View view){
        Intent intent = new Intent(this, UpdateOwnerProfile.class);
        startActivity(intent);
    }

    public void goto_creategym(View view){
        Intent intent = new Intent(this, CreateGym.class);
        startActivity(intent);
    }
}
