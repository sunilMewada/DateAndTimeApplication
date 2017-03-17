package com.sunil.dateandtimeapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button btnChangeDate,btnTime;
    private DatePicker dpResult;
    private int year;
    private int month;
    private int day;

    public static final int DATE_DIALOG_ID =999 ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentDateOnView();
        addListenerOnButton();
        btnTime = (Button)findViewById(R.id.timeButton);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TimeActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentDateOnView() {
        text = (TextView)findViewById(R.id.textView1);
        dpResult = (DatePicker)findViewById(R.id.datepicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        text.setText(new StringBuilder().append(day).append("/").append(month+1).append("/").append(year).append(" "));
        dpResult.init(year,month,day,null);
    }

    private void addListenerOnButton() {
        btnChangeDate = (Button) findViewById(R.id.dateButton);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID :
                return new DatePickerDialog(this,datePickerListener,year,month,day);
        }
        return null;
    }

       private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
               year = selectedYear;
               month = selectedMonth;
               day = selectedDay;
               text.setText(new StringBuilder().append(day).append("/").append(month+1).append("/").append(year).append(" "));
               dpResult.init(year,month,day,null);
           }
       };
}
