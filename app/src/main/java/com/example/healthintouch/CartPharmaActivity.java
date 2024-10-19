package com.example.healthintouch;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartPharmaActivity extends AppCompatActivity {
    HashMap<String, String> item;
    SimpleAdapter sa;

    private Button btnBack, btnCheckOut, dateButton;
    TextView tvtotal;
    ListView lst;
    ArrayList list;
    private String[][]packages ={};
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_pharma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvtotal=findViewById(R.id.textViewPharmaCarttotal);
        lst = findViewById(R.id.listViewPharmaCart);
        btnBack = findViewById(R.id.buttonPharmaCartBack);
        btnCheckOut=findViewById(R.id.buttonPharmaCheckout);
        dateButton=findViewById(R.id.buttonPharmaCartDate);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database db = new Database(getApplicationContext(),"HealthInTouch",null,1);
        float totalAmount =0;

        ArrayList dbData = db.getCartData(username,"pharmacy");
        packages = new String[dbData.size()][];
        for(int i =0;i<packages.length;i++){
            packages[i]= new String[6];
        }
        for(int i = 0; i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String [] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost: "+strData[1]+"/-";
            totalAmount =totalAmount+Float.parseFloat(strData[1]);
        }
        tvtotal.setText("Total Cost: "+totalAmount);

        list = new ArrayList();
        for(int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][5]);
            item.put("line6", "Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1", "line2", "line3", "line4", "line5","line6"},new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,R.id.line_f});
        lst.setAdapter(sa);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartPharmaActivity.this,PharmaBookActivity.class);
                it.putExtra("price",tvtotal.getText());
                it.putExtra("date",dateButton.getText().toString());
                startActivity(it);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartPharmaActivity.this,PharmaActivity.class));
            }
        });
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 datePickerDialog.show();
            }
        });

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                 i1 = i1+1;
                dateButton.setText(i2 +"/"+ i1 + "/" + i);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }
}