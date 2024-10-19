package com.example.healthintouch;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
    EditText regusername, email, regpassword, regcnfrmpd;
    TextView existinguser;
    Button Registerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        regusername = findViewById(R.id.regusername);
        regpassword = findViewById(R.id.regpassword);
        regcnfrmpd = findViewById(R.id.regcnfrmpd);
        Registerbutton = findViewById(R.id.Registerbutton);
        email = findViewById(R.id.mail);
        existinguser = findViewById(R.id.existinguser);
        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regur = regusername.getText().toString();
                String mail = email.getText().toString();
                String regpd = regpassword.getText().toString();
                String regcnfmpd = regcnfrmpd.getText().toString();
                Database db = new Database(getApplicationContext(), "HealthInTouch",null ,1 );
                if (regur.isEmpty() || mail.length() == 0 || regpd.isEmpty() || regcnfrmpd.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (regpd.compareTo(regcnfmpd) == 0) {
                        if(isValid(regpd)){
                            db.register(regur,mail,regpd);
                            Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Password must contain character,numeric and special character", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        existinguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    public static boolean isValid(String password) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (password.length() < 8) {
            return false;
        } else {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLetter((password.charAt(i)))) {
                    f1 = 1;
                }
            }
            for (int j = 0; j < password.length(); j++) {
                if (Character.isDigit(password.charAt(j))) {
                    f2 = 1;
                }
            }
            for (int k = 0; k < password.length(); k++) {
                char c =password.charAt(k);
                if (c>=33 && c<=46 || c==64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1) {
                return true;
            }
            return false;
        }
    }
}
