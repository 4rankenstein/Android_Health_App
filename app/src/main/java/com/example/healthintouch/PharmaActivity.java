package com.example.healthintouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class PharmaActivity extends AppCompatActivity {
    private String [][] packages = {
            {"Uprise-30 1000IU Capsule","","","","","50"},
            {"HealthVit chromium Picolinate 200mcg Capsule","","","","","305"},
            {"Vitamin B Complex Capsules","","","","","448"},
            {"Strepsils Medicated Lozenges for Sore Throat","","","","","40"},
            {"Feronica -XT Tablet","","","","","130"}
            };
    private String[] package_details ={
            "Building and keeping the bones and teeth strong\n"+"Reducing Fatigue/stress and muscular pains\n"+
                    "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace of mineral that plays an important role in helping insulin regulation\n"+
                    "Provides relief from vitamin B deficiencies\n"+
                    "Helps in formation of red blood cells\n"+
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n"+"It helps reduce skin blemish and pigmentation.\n"+
                    "It acts as safeguard the sking from the harsh UVA and UVB sun rays.",
            "Relieves the symptoms of bacterial throat infection and soothes the recovery orocess\n"+
                "Provides a warm and comforting feeling during sore throat",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button back,gotocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pharma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lst = findViewById(R.id.listviewpharma);
        back = findViewById(R.id.buttonpharmaGoBack);
        gotocart = findViewById(R.id.buttonpharmaGoToCart);

        list = new ArrayList();
        for(int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            item.put("line6", "Total Cost:"+packages[i][5]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1", "line2", "line3", "line4", "line5","line6"},new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,R.id.line_f});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(PharmaActivity.this,PharmaDetailedActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][5]);
                startActivity(it);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PharmaActivity.this, HomeActivity.class));
            }
        });
        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PharmaActivity.this,CartPharmaActivity.class ));
            }
        });


    }
}