package com.themodernbit.emerald.NotificationTaskerPackage;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

import com.themodernbit.emerald.MainActivity;
import com.themodernbit.emerald.R;

/**
 * Created by antho on 1/15/2018.
 */

public class NotificationTaskerService extends IntentService {

    private String stringToDisplay = "";
    private static final int NOTIFICATION_ID = 3;


    public NotificationTaskerService(){
        super("NotificationTaskerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Did You Know?");
        builder.setContentText(stringToDisplay);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification

        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }

    public void updateString(String newString){
        stringToDisplay = newString;
    }

}
