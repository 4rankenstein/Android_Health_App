package com.example.healthintouch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    TextView user;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username=findViewById(R.id.regusername);
        password=findViewById(R.id.regpassword);
        login=findViewById(R.id.Registerbutton);
        user=findViewById(R.id.existinguser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = username.getText().toString();
                String pd = password.getText().toString();
                Database db = new Database(getApplicationContext(), "HealthInTouch",null ,1 );
                if(un.isEmpty() || pd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(un,pd)==1){
                    //Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Welcome "+un, Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",un);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }
            }
        }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        }
    }
