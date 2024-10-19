package com.example.healthintouch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthArticlesDetailedActivity extends AppCompatActivity {
    TextView tv1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles_detailed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1 = findViewById(R.id.textViewHADTitle);
        img = findViewById(R.id.imageViewHAD);
        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }
    }
}