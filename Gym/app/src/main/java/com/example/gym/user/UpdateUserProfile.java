package com.example.gym.user;

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

public class UpdateUserProfile extends AppCompatActivity {
    private int SELECT_PICTURE = 200;
    private EditText mobile;
    private EditText email;
    private EditText nameofgym;

    private ImageView userIVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateuserprofile);

        Intent intent = getIntent();
    }

    public void update_userprofile(View v){

        mobile = findViewById(R.id.usermobile);
        email = findViewById(R.id.useremail);
        nameofgym = findViewById(R.id.usergymname);


        // try to send loginid also
        JSONArray arr = new JSONArray();
        arr.put(mobile.getText().toString());
        arr.put(email.getText().toString());
        arr.put(nameofgym.getText().toString());
        JSONArray finalarray = new JSONArray();
        finalarray.put(arr);

        String url = "http://10.0.2.2:5000/updateuserprofile";
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
    }

    //code to choose the image
    public void userimageChooser(View v) {

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

        userIVPreviewImage = findViewById(R.id.userpic);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    userIVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
}
