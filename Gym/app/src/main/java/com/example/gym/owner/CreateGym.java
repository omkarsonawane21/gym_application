package com.example.gym.owner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gym.R;
import com.example.gym.login.Login;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

public class CreateGym extends AppCompatActivity {
    private TextView gymname;
    private TextView gymmobile;
    private TextView gymemail;
    private TextView gymaddress;

    private int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creategym);
        Intent intent = getIntent();
    }

    public void registergym(View v){
        gymname = findViewById(R.id.gymname);
        gymmobile = findViewById(R.id.gymmobile);
        gymemail = findViewById(R.id.gymemail);
        gymaddress = findViewById(R.id.gymaddress);

        JSONArray arr = new JSONArray();
        arr.put(gymname.getText().toString());
        arr.put(gymmobile.getText().toString());
        arr.put(gymaddress.getText().toString());
        arr.put(gymemail.getText().toString());

        JSONArray finalarray = new JSONArray();
        finalarray.put(arr);

        String url = "http://10.0.2.2:5000/register";
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
                // Add headers
                headers.put("Content-Type","application/json");
                return headers;
            }

        };
        rs.add(request_json);

        Intent intent = new Intent(this, Login.class);
        Toast.makeText(this, gymname.getText().toString() + " Gym Registered successfully!", Toast.LENGTH_SHORT).show();
        // send data to server
        startActivity(intent);
    }

    //code to choose the image
    public void gymimageChooser(View v) {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    IVPreviewImage.setImageURI(selectedImageUri);
//                }
            }
        }
    }
}
