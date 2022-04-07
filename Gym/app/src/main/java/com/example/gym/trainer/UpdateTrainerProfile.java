package com.example.gym.trainer;

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

public class UpdateTrainerProfile extends AppCompatActivity {
    private EditText mobile;
    private EditText email;
    private EditText info;
    private EditText gymname;
    private int SELECT_PICTURE = 200;

    private ImageView trainerIVPreviewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatetrainerprofile);

        Intent intent = getIntent();
    }

    //code to choose the image
    public void trainerimageChooser(View v) {

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

//        IVPreviewImage = findViewById(R.id.trainerpic);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the SELECT_PICTURE constant
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

    public void update_trainerprofile(View v){
        mobile = findViewById(R.id.trainermobile);
        email = findViewById(R.id.traineremail);
        gymname = findViewById(R.id.trainersgym);
        info = findViewById(R.id.trainerinfo);

        JSONArray arr = new JSONArray();
        arr.put(mobile.getText().toString());
        arr.put(email.getText().toString());
        arr.put(gymname.getText().toString());
        arr.put(info.getText().toString());
        JSONArray finalarray = new JSONArray();
        finalarray.put(arr);

        String url = "http://10.0.2.2:5000/updatetrainerprofile";
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
}
