package com.sunil.dateandtimeapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by Sunil on 16-03-2017.
 */

public class TimeActivity extends AppCompatActivity {

    private TextView textView;
    private TimePicker timePicker;
    private Button btnTime;
    private int hour;
    private int minute;
    private int seconds;
    private int milliseconds;

    public static final int TIME_DIALOG_ID = 999;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_activity);

        setCurrentTimeOnView();
        addListenerOnButton();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentTimeOnView() {
        textView = (TextView)findViewById(R.id.textView2);
        timePicker = (TimePicker)findViewById(R.id.timerPicker);
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        seconds = c.get(Calendar.SECOND);
        milliseconds = c.get(Calendar.MILLISECOND);

        textView.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)).append(":").append(pad(seconds)).append(":").append(pad(milliseconds)));
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }

    private void addListenerOnButton() {
        btnTime = (Button)findViewById(R.id.timeButton);
        btnTime.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,timePickerListener,hour,minute,false);
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            textView.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)).append(":").append(pad(seconds)).append(":").append(pad(milliseconds)));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
    };
    private static String pad(int c) {
        if (c >=10)
            return String.valueOf(c);
        else
            return "0"+String.valueOf(c);
    }
}
