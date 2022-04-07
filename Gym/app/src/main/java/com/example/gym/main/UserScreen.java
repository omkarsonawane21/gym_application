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
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gym.R;
import com.example.gym.login.Login;
import com.example.gym.user.UpdateUserProfile;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

public class UserScreen extends AppCompatActivity {
    private TextView uname;
    private TextView userloginid;

    private String name;
    private String id;

    private ImageView userimageview;
    private RecyclerView userrecyclerView;
    String [] arr = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userscreen);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.userviewpager);
//        viewPager.setAdapter(new UserPagerAdapter(this));
        Intent intent = getIntent();
        name = intent.getStringExtra(Login.username);

        uname = findViewById(R.id.uname);
        uname.setText(name);

        id = intent.getStringExtra(Login.loginid);
        userloginid = findViewById(R.id.userloginid);
        userloginid.setText(id);
        userrecyclerView = findViewById(R.id.userscreenrecyclerview);
        userrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(arr);
        userrecyclerView.setAdapter(userAdapter);

        userimageview = findViewById(R.id.userimageView);
        JSONArray arr = new JSONArray();
        arr.put(id);
        String url = "http://10.0.2.2:5000/senduserprofilepic";
        RequestQueue rs = Volley.newRequestQueue(this);

        // we are sending only 1D array i.e arr
        JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, url, arr,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                // this is the image in bytes
                                System.out.println(response.get(i).toString());
                                display_userprofile(userimageview, response.get(i).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, volleyError -> VolleyLog.e("Error: ", volleyError.getMessage())) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // Add headers
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        rs.add(request_json);
    }

    public void display_userprofile(ImageView imageView, String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
    }

    public void goto_userupdateprofile(View view){
        Intent intent = new Intent(this, UpdateUserProfile.class);
        startActivity(intent);
    }
}
