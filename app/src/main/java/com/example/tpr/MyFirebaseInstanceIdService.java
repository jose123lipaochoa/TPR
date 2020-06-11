package com.example.tpr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("TAG","Mensaje recibido:"+remoteMessage.getFrom());
        if(remoteMessage.getNotification()!=null){
            Log.d("TAG","Notificacion"+remoteMessage.getNotification().getBody());
            mostrarNotificacion(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
    }
    private void mostrarNotificacion(String title,String body){
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBAR=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.busceleste)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound);
        NotificationManager ntm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        ntm.notify(0, notificationBAR.build());
    }
}
