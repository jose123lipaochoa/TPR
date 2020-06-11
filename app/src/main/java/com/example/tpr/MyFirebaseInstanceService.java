package com.example.tpr;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s){
        super.onNewToken(s);
        Log.d("TAG",s);
    }
}
