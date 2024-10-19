package com.example.healthintouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {
    private  String[][] health_details = {{"Benifits of Walking Daily"," ","",""," ","Click for more details"},
            {"Smoking Drawbacks"," ","","","","Click for more details"},
            {"Get a Healthy Gut"," ","","","","Click for more details"},
            {"Preventions for Menstrual Cramps"," ","","","","Click for more details"},
            {"Covid19 Homecare"," ","","","","Click for more details"}};
    private int[] images = {R.drawable.walkingdaily,R.drawable.smoking,R.drawable.healthygut,R.drawable.menstrualcramps,R.drawable.covid19};
    HashMap<String,String>  item;
    ArrayList list;
    ListView lst;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lst = findViewById(R.id.listView);
        list = new ArrayList();
        for(int i = 0; i < health_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            item.put("line6", health_details[i][5]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1", "line2", "line3", "line4", "line5","line6"},new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,R.id.line_f });
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticlesDetailedActivity.class);
                //it.putExtra("text1", title);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });
    }
}