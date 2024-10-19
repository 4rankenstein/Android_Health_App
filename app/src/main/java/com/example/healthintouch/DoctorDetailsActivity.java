package com.example.healthintouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class    DoctorDetailsActivity extends AppCompatActivity {
    private final String[][] doctor_details1 = {
            {"Hospital Name: Citizen Hospital", "Doctor Name: Arjit Sastry", "Address : Kothapeta, Guntur", "Experience: 5yrs", "Phone Number: 0866-254656", "Consultation: 300"},
            {"Hospital Name: Sunshine Hospital", "Doctor Name: Manikanta Thakre", "Address : Ajit Singhnagar,PNT Colony, Vijayawada", "Experience: 5yrs", "Phone Number: 0866-264796", "Consultation: 300"},
            {"Hospital Name: Capital Hospital", "Doctor Name: G. Jwala Narashima Rao", "Adress :Poranki, Vijayawada ", "Experience: 25yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Capital Hospital", "Doctor Name: VSR Bhupal", "Adress :Poranki, Vijayawada ", "Experience: 5yrs", "Phone Number: 085285 19999", "Consultation: 200"},
            {"Hospital Name: Capital Hospital", "Doctor Name: M. Kavita Priyanka", "Adress :Poranki, Vijayawada ", "Experience: 3yrs", "Phone Number: 085285 19999", "Consultation: 400"}};
    private final String[][] doctor_details2 = {
            {"Hospital Name: Ramesh Hospital", "Doctor Name: PVN Rao", "Address : Benz circle, Vijayawada", "Experience: 5yrs", "Phone Number: 0866-254286", "Consultation: 200"},
            {"Hospital Name: Sunshine Hospital", "Doctor Name: D Manikanta ", "Address : Ajit Singhnagar,PNT Colony, Vijayawada", "Experience: 5yrs", "Phone Number: 0866-264796", "Consultation: 200"},
            {"Hospital Name: Capital Hospital", "Doctor Name: G. Bhanu Prakash", "Adress :Poranki, Vijayawada ", "Experience: 25yrs", "Phone Number: 085285 19999", "Consultation: 300"},
            {"Hospital Name: Liberty Hospitals", "Doctor Name: B. Gowtham", "Adress :Autonagar, Vijayawada ", "Experience: 5yrs", "Phone Number: 085285 19999", "Consultation: 200"},
            {"Hospital Name: Time Hospital", "Doctor Name: M. Kavita Priyanka", "Adress :Bandar Road, Vijayawada ", "Experience: 3yrs", "Phone Number: 085285 19999", "Consultation: 300"}};

    private final String[][] doctor_details3 = {
            {"Hospital Name: Citizen Hospital", "Doctor Name: B.Sowmya", "Address : Kothapeta, Guntur", "Experience: 5yrs", "Phone Number: 0866-254656", "Consultation: 500"},
            {"Hospital Name: Sunshine Hospital", "Doctor Name: G.Rakesh", "Address : Ajit Singhnagar,PNT Colony, Vijayawada", "Experience: 5yrs", "Phone Number: 0866-264796", "Consultation: 500"},
            {"Hospital Name: Capital Hospital", "Doctor Name: B.Gowtham", "Adress :Poranki, Vijayawada ", "Experience: 25yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Ramesh Hospital", "Doctor Name: K.Tarun", "Adress :Labbipet, Vijayawada ", "Experience: 5yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Harini Hospital", "Doctor Name: P.Niharika", "Adress :Pushpa Hotel, Vijayawada ", "Experience: 3yrs", "Phone Number: 085285 19999", "Consultation: 500"}};
    private final String[][] doctor_details4 = {
            {"Hospital Name: Citizen Hospital", "Doctor Name: K.Alekhya", "Address : Kothapeta, Guntur", "Experience: 5yrs", "Phone Number: 0866-254656", "Consultation: 500"},
            {"Hospital Name: Sunshine Hospital", "Doctor Name: Ch.Roopa", "Address : Ajit Singhnagar,PNT Colony, Vijayawada", "Experience: 5yrs", "Phone Number: 0866-264796", "Consultation: 500"},
            {"Hospital Name: Capital Hospital", "Doctor Name: G.Suhas", "Adress :Poranki, Vijayawada ", "Experience: 25yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Rainbow Hospital", "Doctor Name: VSR Bhupal", "Adress :Pushpa Hotel Center, Vijayawada ", "Experience: 5yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Sowmya Hospital", "Doctor Name: M. Kavita Priyanka", "Adress :Poranki, Vijayawada ", "Experience: 3yrs", "Phone Number: 085285 19999", "Consultation: 500"}};
    private final String[][] doctor_details5 = {
            {"Hospital Name: Citizen Hospital", "Doctor Name: Arjit Sastry", "Address : Kothapeta, Guntur", "Experience: 5yrs", "Phone Number: 0866-254656", "Consultation: 500"},
            {"Hospital Name: Sunshine Hospital", "Doctor Name: Manikanta Thakre", "Address : Ajit Singhnagar,PNT Colony, Vijayawada", "Experience: 5yrs", "Phone Number: 0866-264796", "Consultation: 500"},
            {"Hospital Name: Capital Hospital", "Doctor Name: G. Jwala Narashima Rao", "Adress :Poranki, Vijayawada ", "Experience: 25yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Capital Hospital", "Doctor Name: VSR Bhupal", "Adress :Poranki, Vijayawada ", "Experience: 5yrs", "Phone Number: 085285 19999", "Consultation: 500"},
            {"Hospital Name: Chaitanya Hospital", "Doctor Name: M. Kavita Priyanka", "Adress :Opp PB Siddhartha college, Vijayawada ", "Experience: 3yrs", "Phone Number: 085285 19999", "Consultation: 500"}};
    private int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
    TextView titleDD;
    String[][] doctor_details = {};
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bt = findViewById(R.id.button2);
        titleDD = findViewById(R.id.titleDD);
        Intent it = getIntent();                                        // to pass the msg from find doctor to doctor details
        String title = it.getStringExtra("title");
        titleDD.setText(title);
        if (title.compareTo("General Physician") == 0) {
            doctor_details = doctor_details1;}
        else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctor_details2;}
        else if (title.compareTo("Dentist") == 0) {
            doctor_details = doctor_details3;}
        else if (title.compareTo("Dietitian") == 0) {
            doctor_details = doctor_details4;}
        else if (title.compareTo("Cardiologist") == 0) {
            doctor_details = doctor_details5;}

            list = new ArrayList();
            for (int i = 0; i < doctor_details.length; i++) {
                item = new HashMap<String, String>();
                item.put("line1", doctor_details[i][0]);
                item.put("line2", doctor_details[i][1]);
                item.put("line3", doctor_details[i][2]);
                item.put("line4", doctor_details[i][3]);
                item.put("line5", doctor_details[i][4]);
                item.put("line6", doctor_details[i][5]);
                list.add(item);
            }

            sa = new SimpleAdapter(this, list, R.layout.multilines2, new String[]{"line1", "line2", "line3", "line4", "line5", "line6"}, new int[]{R.id.cname, R.id.dname, R.id.address, R.id.exp, R.id.contact, R.id.fees});
            ListView lst = findViewById(R.id.listviewDD);
            lst.setAdapter(sa);
            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                    it.putExtra("text1", title);
                    it.putExtra("text2", doctor_details[i][1]);
                    it.putExtra("text3", doctor_details[i][2]);
                    it.putExtra("text4", doctor_details[i][4]);
                    it.putExtra("text5", doctor_details[i][5]);
                    startActivity(it);
                }
            });
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
                }
            });
        }
    }
