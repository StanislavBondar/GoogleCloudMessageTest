package com.example.zloj.googlecloudmessagetest.gcm;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.zloj.googlecloudmessagetest.MainActivity;
import com.example.zloj.googlecloudmessagetest.R;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by zloj on 05.01.16.
 */
public class GCMListener extends GcmListenerService {

    PowerManager pm;
    PowerManager.WakeLock wl;

    @Override
    public void onMessageReceived(String from, final Bundle data) {
        super.onMessageReceived(from, data);
        System.out.println("MESSAGE RECEIVED: " + data.toString());
        Handler mHandler = new Handler(this.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                wl = pm.newWakeLock(
                        PowerManager.SCREEN_DIM_WAKE_LOCK
                                | PowerManager.ON_AFTER_RELEASE
                                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "bbbb");
//                wl.acquire(15000);
                wl.acquire();
                KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
                final KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
                lock.disableKeyguard();
                notification(data);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        wl.release();
                        lock.reenableKeyguard();
                    }
                });
            }
        });
    }

    protected void notification(Bundle message) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(message.getString("title"))
                        .setContentText(message.getString("message"));

        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.mix);
        mPlayer.start();

// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }

    private String parseJson(Bundle bundle) {
        final String mMessage = "message";
        String mResult = "";
        try {
//            JSONObject mObject = new JSONObject(bundle.toString());
            mResult = bundle.getString(mMessage);
            return mResult;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }
}
