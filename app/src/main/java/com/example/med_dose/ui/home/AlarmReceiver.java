package com.example.med_dose.ui.home;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.med_dose.MainActivity;
import com.abdsoft.med_dose.R;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;
import static android.content.Context.ALARM_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "com.med_dose.notificationRemId";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("canceL","alarm on receive called");
        Intent notificationIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle("Medicine Reminder!")
                .setContentText("Time to take " + intent.getStringExtra("medicineName"))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Time to take " + intent.getStringExtra("medicineName")))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .setAutoCancel(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Medicine Dosage Reminder ",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, builder.build());
    }

    public void cancelNotification(Context context, int NotifyId) {
        /*Intent intentCancel = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(context,NotifyId,intentCancel,0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel("cancel",NotifyId);*/
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        //notificationIntent.putExtra("medicineName", medicineName);

        PendingIntent broadcast = PendingIntent.getBroadcast(context, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(broadcast);
    }
}