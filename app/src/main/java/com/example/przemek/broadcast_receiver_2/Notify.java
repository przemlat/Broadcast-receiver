package com.example.przemek.broadcast_receiver_2;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

public class Notify extends IntentService {
    private static final String ACTION_NOTIFY = "com.example.patryko.last_receiver.action.NOTIFY";

    private static final String EXTRA_MESSAGE = "com.example.patryko.last_receiver.extra.MESSAGE";

    private int id = 0;

    public Notify() {
        super("Notify");
    }
    public static void startActionNotify(Context context, String message){

        Intent intent = new Intent(context, Notify.class);
        intent.setAction(ACTION_NOTIFY);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.startService(intent);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFY.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_MESSAGE);
                handleActionNotify(param1);
            }
        }
    }

    private void handleActionNotify(String message){
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.mojanotyfikacja);


        String str_title = message;



        Intent intent = new Intent(this, MyNotification.class);
        intent.putExtra("title", str_title);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pIntent)
                .setContent(remoteViews);

        remoteViews.setTextViewText(R.id.title, str_title);


        NotificationManager notman = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notman.notify(id++, builder.build());

    }
}
