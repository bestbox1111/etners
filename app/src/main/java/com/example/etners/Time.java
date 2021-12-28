package com.example.etners;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static android.widget.Toast.*;

public class Time extends AppCompatActivity {


    TextView time1, time2, work1, work2;

    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);



        time2=findViewById(R.id.time2);
        work2=findViewById(R.id.work2);
        time1=findViewById(R.id.time1);
        work1=findViewById(R.id.work1);

        intent= getIntent();
        String time22= intent.getStringExtra("time2");
        String work22= intent.getStringExtra("work2");

        time1.setBackgroundColor(Color.GRAY);
        time2.setBackgroundColor(Color.GRAY);
        time2.setText(time22);


        work1.setBackgroundColor(Color.GRAY);
        work2.setBackgroundColor(Color.GRAY);
        work2.setText(work22);





    }


}