package com.example.etners;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static android.content.Intent.getIntent;


public class MyReceiver extends BroadcastReceiver {

    NotificationManager notificationManager;
    NotificationCompat.Builder builder ;
//    ArrayList<Timememo> arrayList;
    static int count;



    @Override
    public void onReceive(Context context, Intent intent) {

    count++;


        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

//        throw new UnsupportedOperationException("Not yet implemented");

         notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelId = "one-channel";
            String channelName = "온양 포스트 사내물류 ";
            String channelDescription = "모를땐 막 눌러보기 ";

            NotificationChannel channel = new NotificationChannel(channelId, channelName
                    , NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription(channelDescription);

            channel.enableLights(true);
            channel.setLightColor(Color.GRAY);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100,200,300});

            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(context, channelId);





        }else{
            builder = new NotificationCompat.Builder(context);


        }

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
//        builder.setWhen(System.currentTimeMillis());
         String time = intent.getStringExtra("time");   //노티피케이션이 동작했을때 받아오는 인텐트에 실린 타임값.
         String work= intent.getStringExtra("memo");    //노티피케이션이 동작했을때 받아오는 인텐트에 실린 워크값.



        builder.setContentTitle("지정한 시간 : "+time);
        builder.setContentText("해야할 업무 : " +work);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);



        Intent intent1 = new Intent(context,Time.class);

        intent1.putExtra("time2",time);
        intent1.putExtra("work2",work);


        PendingIntent pendingIntent1 = PendingIntent.getActivity(context,count ,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent1);



        notificationManager.notify(count ,builder.build());





    }

}