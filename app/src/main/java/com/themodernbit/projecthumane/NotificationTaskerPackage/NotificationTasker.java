package com.themodernbit.projecthumane.NotificationTaskerPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by antho on 1/15/2018.
 */

public class NotificationTasker extends BroadcastReceiver{

    public NotificationTasker(){

    }


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent theIntent = new Intent(context, NotificationTaskerService.class);
        context.startService(theIntent);

    }
}
