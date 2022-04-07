package com.example.gym.owner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gym.R;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

public class UpdateOwnerProfile extends AppCompatActivity {
    private int SELECT_PICTURE = 200;
    private EditText mobile;
    private EditText email;
    private EditText gymname;
    private ImageView ownerIVPreviewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateownerprofile);
        Intent intent = getIntent();
    }

    public void ownerimageChooser(View v) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ownerIVPreviewImage = findViewById(R.id.ownerpic);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    ownerIVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public void update_ownerprofile(View v){
        mobile = findViewById(R.id.ownermobile);
        email = findViewById(R.id.owneremail);
        gymname = findViewById(R.id.ownergymname);

        JSONArray arr = new JSONArray();
        arr.put(mobile.getText().toString());
        arr.put(email.getText().toString());
        arr.put(gymname.getText().toString());
        JSONArray finalarray = new JSONArray();
        finalarray.put(arr);

        String url = "http://10.0.2.2:5000/updateownerprofile";
        RequestQueue rs = Volley.newRequestQueue(this);

        JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, url, arr,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Get Final response
                        for(int i = 0; i < response.length(); i++){
                            try {
                                System.out.println(response.get(i).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, volleyError -> VolleyLog.e("Error: ", volleyError.getMessage())) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        rs.add(request_json);
    }
}
