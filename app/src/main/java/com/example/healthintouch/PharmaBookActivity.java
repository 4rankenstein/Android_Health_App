package com.example.healthintouch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class PharmaBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact,edpincode;
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pharma_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edname = findViewById(R.id.pharmabookname);
        edaddress = findViewById(R.id.pharmabookaddress);
        edcontact = findViewById(R.id.pharmabookcontact);
        edpincode = findViewById(R.id.pharmabookpincode);
        order = findViewById(R.id.pharmabookorder);

        Intent intent = getIntent();
        String []price = intent.getStringExtra("price").toString().split(Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"HealthInTouch",null, 1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"pharmacy");
                db.removeCart(username,"pharmacy");
                Toast.makeText(getApplicationContext(), "Your booking is successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PharmaBookActivity.this, HomeActivity.class));
            }
        });
    }
}