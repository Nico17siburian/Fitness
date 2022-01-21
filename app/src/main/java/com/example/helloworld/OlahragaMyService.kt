package com.example.helloworld

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class OlahragaMyService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Lakukan Gerakan", Toast.LENGTH_SHORT).show()
        Thread(Runnable {
            for(i in 0..10)
                try {
                    Thread.sleep(1000L)
                }
                catch (e: Exception){

                }
            stopSelf()
        }).start()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Gerakan Selesai", Toast.LENGTH_SHORT).show()
    }
}