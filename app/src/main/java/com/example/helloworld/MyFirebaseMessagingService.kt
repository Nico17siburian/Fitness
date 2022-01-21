package com.example.helloworld

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if(message.notification != null){
            showNotify(message.notification!!.title, message.notification!!.body)
        }
    }

    private fun showNotify(title: String?, message: String?) {
        var myNotifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val myNotificationChannel = NotificationChannel("channel1","Notification FCM",
            NotificationManager.IMPORTANCE_DEFAULT)
            myNotificationChannel.description = "FCM Channel 1"
            myNotificationChannel.enableLights(true)
            myNotificationChannel.lightColor = Color.MAGENTA
            myNotifyManager.createNotificationChannel(myNotificationChannel)
        }
        var myNotify = NotificationCompat.Builder(this, "channel1").apply {
            setDefaults(Notification.DEFAULT_ALL)
            setWhen(System.currentTimeMillis())
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(message)
            setContentInfo("Info")
        }
        myNotifyManager.notify(1,myNotify.build())
    }
}