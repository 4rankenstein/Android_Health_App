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

public class PharmaDetailedActivity extends AppCompatActivity {
    TextView t1,t2;
    EditText ed;
    Button btback,btaddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pharma_detailed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        t1=findViewById(R.id.textViewPharmaTotalCost);
        t2=findViewById(R.id.textViewPharmaPackageName);
        ed = findViewById(R.id.editTextmultiLine);
        ed.setKeyListener(null);
        btback= findViewById(R.id.buttonPharmaGoBack);
        btaddtocart = findViewById(R.id.buttonPharmaAddToCart);
        Intent intent = getIntent();
        t2.setText(intent.getStringExtra("text1"));
        ed.setText(intent.getStringExtra("text2"));
        t1.setText("Total cost:"+intent.getStringExtra("text3"));
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PharmaDetailedActivity.this,PharmaActivity.class));
            }
        });
        btaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product =t2.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db = new Database(getApplicationContext(),"HealthInTouch",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(PharmaDetailedActivity.this, "Product already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addCart(username, product, price, "pharmacy");
                    Toast.makeText(PharmaDetailedActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(PharmaDetailedActivity.this,PharmaActivity.class ));
                }
            }
        });

    }
}