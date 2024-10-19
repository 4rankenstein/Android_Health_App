package com.example.healthintouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctorActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });

        CardView physician =findViewById(R.id.cardPhysician);
        physician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","General Physician");
                startActivity(it);
            }
        });
        CardView surgeon =findViewById(R.id.cardSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });
        CardView dentist =findViewById(R.id.cardDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });
        CardView dietitian =findViewById(R.id.cardDietitian);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Dietitian");
                startActivity(it);
            }
        });
        CardView cardiologist =findViewById(R.id.cardCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });
    }
}