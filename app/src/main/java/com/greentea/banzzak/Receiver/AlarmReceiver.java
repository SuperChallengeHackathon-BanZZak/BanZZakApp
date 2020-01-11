package com.greentea.banzzak.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.greentea.banzzak.AlarmStartActivity;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private static PowerManager.WakeLock wakeLock;

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK|
                PowerManager.ACQUIRE_CAUSES_WAKEUP|
                PowerManager.ON_AFTER_RELEASE, "app:alarm");

        wakeLock.acquire();

        this.context = context;
        Intent alarmIntent = new Intent("android.intent.action.sec");

        alarmIntent.setClass(context, AlarmStartActivity.class);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.context.startActivity(alarmIntent);

        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
