package com.example.m_icp2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarView extends AppCompatActivity {

    android.widget.CalendarView calendarView;
    TextView currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        calendarView=(android.widget.CalendarView) findViewById(R.id.calendarView);
        currentDate=(TextView) findViewById(R.id.currentDate);
        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView calendarView, int i, int i1, int i2) {
                String date = "Date: " + (i1 + 1) + "/" + i2 + "/" + i;
                currentDate.setText(date);
            }
        });
        Button butinsert = (Button) findViewById(R.id.create_eventbut);
        butinsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                insert();
            }
        });
    }

    @SuppressLint("NewApi")
    public void insert() {
        Intent intent = new Intent(Intent.ACTION_INSERT,
                CalendarContract.Events.CONTENT_URI);
        // Add the calendar event details
        intent.putExtra(CalendarContract.Events.TITLE, "Launch!");
        intent.putExtra(CalendarContract.Events.DESCRIPTION,
                "Learn Java Android Coding");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                "UMKC.com");
        Calendar startTime = Calendar.getInstance();
        //startTime.set(2017, 12, 1);
        intent.putExtra("beginTime", startTime.getTimeInMillis() );
        intent.putExtra("endTime", startTime.getTimeInMillis()+60*60*1000);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        // Use the Calendar app to add the new event.
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

