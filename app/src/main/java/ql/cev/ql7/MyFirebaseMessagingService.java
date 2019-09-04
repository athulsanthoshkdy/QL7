package ql.cev.ql7;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;



public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager notificationManager;

    @Override public void onMessageReceived(RemoteMessage remoteMessage) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);








        int notificationId = new Random().nextInt(60000);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("message"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId,notificationBuilder.build());


    }

}
