package com.example.gym.signup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // get the textviews from layout
    private TextView name;
    private TextView mobile;
    private TextView aadhar;
    private TextView email;
    private TextView usernames;
    private TextView passwd;

    private String type;

    private Spinner spinner;
//    private ImageView IVPreviewImage;
    // constant to compare the activity result code
    private int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Intent intent = getIntent();
        spinner = (Spinner) findViewById(R.id.typeofregistrar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.typeofregistrar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        type = "User";
    }
    public void signup(View v){
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.gymmobile);
        aadhar = findViewById(R.id.aadhar);
        email = findViewById(R.id.gymemail);
        usernames = findViewById(R.id.username);
        passwd = findViewById(R.id.passwd);

        JSONArray arr = new JSONArray();
        arr.put(usernames.getText().toString());
        arr.put(passwd.getText().toString());
        arr.put(name.getText().toString());
        arr.put(mobile.getText().toString());
        arr.put(aadhar.getText().toString());
        arr.put(email.getText().toString());
        arr.put(type);
//        JSONArray finalarray = new JSONArray();
//        finalarray.put(arr);

        String url = "http://10.0.2.2:5000/register";
        RequestQueue rs = Volley.newRequestQueue(this);

        // we are sending only 1D array i.e arr
        JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, url, arr,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Get Final response
                        for(int i = 0; i < response.length(); i++){
                            try {
                                if(response.get(i).toString().equals("1")){
                                    goto_loginscreen(true);
                                }
                                else{
                                    goto_loginscreen(false);
                                }

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

    public void goto_loginscreen(boolean registered_or_not) {
        if(registered_or_not){
            Intent intent = new Intent(this, Login.class);
            Toast.makeText(this, name.getText().toString() + " Registered successfully!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Error in Registration!", Toast.LENGTH_SHORT).show();
        }
    }
    //code to choose the image
    public void imageChooser(View v) {

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
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);


                }
            }
        }
    }
}
