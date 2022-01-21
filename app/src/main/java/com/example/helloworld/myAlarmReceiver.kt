package com.example.helloworld

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

const val EXTRA_PESAN: String = "EXTRA_PESAN"
class myAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val Notifyid = 30103
        val Channel_id = "my_channel_01"
        val name = "ON/OFF"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val nNotifyChannel = NotificationChannel(Channel_id,
            name,
            importance)
        val mBuilder = NotificationCompat.Builder(context!!,Channel_id)
            .setSmallIcon(R.drawable.ic_bell)
            .setContentText(intent?.getStringExtra(EXTRA_PESAN))
            .setContentTitle("Alarm Kegiatan")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent(
//                PendingIntent.getActivity(
//                context, // Context from onReceive method.
//                0,
//                Intent(context, makanbang::class.java), // Activity you want to launch onClick.
//                0
//            )
//            )
        var mNotificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        for(s in mNotificationManager.notificationChannels){
            mNotificationManager.deleteNotificationChannel(s.id)
        }
        mNotificationManager.createNotificationChannel(nNotifyChannel)
        mNotificationManager.notify(Notifyid,mBuilder.build())
    }
}