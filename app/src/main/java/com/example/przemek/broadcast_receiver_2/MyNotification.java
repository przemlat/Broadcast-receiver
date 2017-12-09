package com.example.przemek.broadcast_receiver_2;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        TextView tvt = (TextView)findViewById(R.id.activity_title);
        tvt.setText(title);
    }
}
