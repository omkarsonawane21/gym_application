package com.example.gym.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gym.R;
import com.example.gym.login.Login;
import com.example.gym.trainer.UpdateTrainerProfile;

import java.io.ByteArrayOutputStream;

public class TrainerScreen extends AppCompatActivity {private int SELECT_PICTURE = 200;
    private TextView trainername;
    private TextView trainerloginid;
    private ImageView image;

    private String name;
    private String id;
    private RecyclerView recyclerView;
    String [] arr = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainerscreen);

        Intent intent = getIntent();
        name = intent.getStringExtra(Login.username);
        trainername = findViewById(R.id.trainername);
        trainername.setText(name);

        id = intent.getStringExtra(Login.loginid);
        trainerloginid = findViewById(R.id.trainerloginid);
        trainerloginid.setText(id);

        recyclerView = findViewById(R.id.trainerscreenrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TrainerAdapter trainerAdapter = new TrainerAdapter(arr);
        recyclerView.setAdapter(trainerAdapter);
        ImageView image = findViewById(R.id.trainerpic);

        // question is regarding how to get encoded image
//        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        image.setImageBitmap(decodedByte);
    }

    public void goto_trainerupdateprofile(View v){
        Intent intent = new Intent(this, UpdateTrainerProfile.class);
        startActivity(intent);
    }
}
