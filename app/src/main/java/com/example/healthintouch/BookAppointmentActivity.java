package com.example.healthintouch;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
TextView textView7,textViewAppFullname,textViewAppAddress,textViewAppContactNumber,textViewAppConsultationFees;
private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;
private Button dateButton,timeButton,bookappointment,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView7 = findViewById(R.id.textView7);
textViewAppFullname = findViewById(R.id.textViewAppFullname);
textViewAppAddress = findViewById(R.id.textViewAppAddress);
textViewAppContactNumber = findViewById(R.id.textViewAppContactNumber);
textViewAppConsultationFees = findViewById(R.id.textViewAppConsultationFees);
dateButton= findViewById(R.id.datebutton);
timeButton=findViewById(R.id.timebutton);
bookappointment=findViewById(R.id.bookappointment);
cancel = findViewById(R.id.cancelappointment);

textView7.setKeyListener(null);
textViewAppFullname.setKeyListener(null);
textViewAppAddress.setKeyListener(null);
textViewAppContactNumber.setKeyListener(null);
textViewAppConsultationFees.setKeyListener(null);

Intent it =getIntent();
String title = it.getStringExtra("text1");
String fullname = it.getStringExtra("text2");
String address = it.getStringExtra("text3");
String contact = it.getStringExtra("text4");
String fees = it.getStringExtra("text5");

textView7.setText(title);
textViewAppFullname.setText(fullname);
textViewAppAddress.setText(address);
textViewAppContactNumber.setText(contact);
textViewAppConsultationFees.setText(fees);
initDatePicker();
dateButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        datePickerDialog.show();
    }
});
initTimePicker();
timeButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        timePickerDialog.show();
    }
});
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
    }
});
bookappointment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Database db = new Database(getApplicationContext(),"HealthInTouch",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        if(db.checkAppointmentExists(username,title+"=>"+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1) {
            Toast.makeText(BookAppointmentActivity.this, "Appointment already booked", Toast.LENGTH_SHORT).show();
        }
        else{
            db.addOrder(username,title+"=>"+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
            Toast.makeText(BookAppointmentActivity.this, "Your appointment is successfully booked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
        }

    }
});
    }

    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);

            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }

}