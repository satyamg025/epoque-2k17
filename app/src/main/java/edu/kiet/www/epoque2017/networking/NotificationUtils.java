package edu.kiet.www.epoque2017.networking;

/**
 * Created by satyam on 2/21/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Splash.SplashActivity;
import edu.kiet.www.epoque2017.util.DbHandler;


public class NotificationUtils extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title=remoteMessage.getNotification().getTitle();
        String message=remoteMessage.getNotification().getBody();

        if(DbHandler.getString(this,"bearer","").equals("")){
            Intent intent=new Intent(this,SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
            notification.setContentTitle(title);
            notification.setContentText(message);
            Uri notify = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notify);
            r.play();
            // notification.setVibrate(new long[] { 700,700 });
            notification.setSmallIcon(R.drawable.ic_noti);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notification.setColor(getResources().getColor(R.color.black));
            }
            notification.setAutoCancel(false);
            notification.setContentIntent(pendingIntent);
            NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,notification.build());



        }
        else {

            Intent intent = new Intent(this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
            notification.setContentTitle(title);
            notification.setContentText(message);
            Toast.makeText(this,title+message,Toast.LENGTH_SHORT).show();
            Uri notify = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notify);
            r.play();
            // notification.setVibrate(new long[] { 700,700 });
            notification.setSmallIcon(R.drawable.splash_background);
            notification.setAutoCancel(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notification.setColor(getResources().getColor(R.color.black));
            }
            notification.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification.build());

            if(DbHandler.contains(this,"title_list")){
                String get_title=DbHandler.getString(this,"title_list","");
                String get_message=DbHandler.getString(this,"message_list","");
                get_title=get_title+"@#$"+title;
                get_message=get_message+"@#$"+message;
                DbHandler.putString(this,"title_list",get_title);
                DbHandler.putString(this,"message_list",get_message);
            }
            else{
                DbHandler.putString(this,"title_list",title);
                DbHandler.putString(this,"message_list",message);
            }
        }

    }
}