package com.example.gym.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.gym.R;
import com.example.gym.main.OwnerScreen;
import com.example.gym.main.TrainerScreen;
import com.example.gym.main.UserScreen;
import com.example.gym.signup.Signup;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Login extends AppCompatActivity {
    // xml
    private EditText uname;
    private EditText password;
    private String nameText;
    private String passText;

    // intents
    public static final String username = "com.example.gym.extra.username";
    public static final String loginid = "com.example.gym.extra.loginid";

    // general use recvd from server
    private String loginid_from_server;

    private boolean username_exist = false, password_exist = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Intent intent = getIntent();

        // get list of data from server to verify with uname and password entered by user
    }
    public void login(View v) throws IOException {
        URL url = new URL("http://10.0.2.2:5000/login");
        URLConnection request = url.openConnection();
        try {
            request.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            line = sb.toString();

            uname = findViewById(R.id.uname);
            password = findViewById(R.id.password);
            nameText = uname.getText().toString();
            passText = password.getText().toString();

            JSONArray arr = new JSONArray(line);
            JSONArray a = null;
            for(int i = 0; i < arr.length(); i++){
                a = new JSONArray(arr.get(i).toString());

                if(nameText.equals(a.get(0).toString())){
                    username_exist = true;
                    if(passText.equals(a.get(1).toString())){
                        password_exist = true;
                        loginid_from_server = a.get(2).toString();
                        break;
                    }
                }
            }
        }
        catch (IOException | JSONException e){
            System.out.println(e);
        }

        if(username_exist){   // means check in the list recvd from server
            if(password_exist){
                Intent intent;
                if(Character.compare(loginid_from_server.charAt(0), '3') == 0) {
                    Toast.makeText(this, "Logged in successfully as Trainer!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(this, TrainerScreen.class);
                }
                else if(Character.compare(loginid_from_server.charAt(0), '4') == 0){
                    Toast.makeText(this, "Logged in successfully as Owner!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(this, OwnerScreen.class);
                }
                else{
                    Toast.makeText(this, "Logged in successfully as User!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(this, UserScreen.class);
                }

                //to pass username and id from one screen to another
                intent.putExtra(username, nameText);
                intent.putExtra(loginid, loginid_from_server);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Incorrect password!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Username doesn't exist!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View v){
        Intent intent1 = new Intent(this, Signup.class);
        startActivity(intent1);
    }
}