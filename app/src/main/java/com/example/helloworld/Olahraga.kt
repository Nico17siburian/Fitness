package com.example.helloworld

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_olahraga.*


private var sp : SoundPool? = null
private var soundID = 0

class Olahraga : AppCompatActivity() {

    private val CHANNEL_ID = "notifikasi"
    private val notificationId = 101

    private val progressReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            var persen = p1?.getIntExtra(EXTRA_PERSEN, 0)
            var selesai = p1?.getBooleanExtra(EXTRA_FINISH, true)

            progress_circular.progress = persen ?: 0
            if(selesai!!){

            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olahraga)

        createNotificationChannel()

        var myService = Intent(this, OlahragaMyService::class.java)
        mulai.setOnClickListener {
            startService(myService)


        }

        var myServiceBar = Intent(this, ProgressService::class.java)
        mulai.setOnClickListener {
            sendNotification()
            ProgressService.enqueueWork(this, myServiceBar)

            if (soundID != 0){
                sp?.play(soundID, 0.99f, .99f,1,0,.99f)
            }
        }

        var filterProgress = IntentFilter(ACTION_PROGRESS)
        registerReceiver(progressReceiver, filterProgress)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(progressReceiver)
    }

    fun next(view: View){
        var intentReply = Intent(this, crunch::class.java)
        startActivity(intentReply)
    }

    fun kembali(view: View){
        var intentReply = Intent(this, MainActivity::class.java)
        startActivity(intentReply)
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Judul Notifikasi"
            val descriptionText = "Deskripsi Notifikasi"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description= descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_energy)
            .setContentTitle("Lompat Bintang")
            .setContentText("Lakukan gerakan selama 10 detik")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }

    override fun onStart() {
        super.onStart()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            createNewSoundPool()
        }
        else{
            createOldSoundPool()
        }
        sp?.setOnLoadCompleteListener { soundPool, id, status ->
            if(status != 0){

            }
        }
        soundID =sp?.load(this,R.raw.mulai,1) ?: 0
        //soundID =sp?.load(this,R.raw.selesai,2) ?: 0
    }

    private fun createOldSoundPool() {
        sp = SoundPool(15, AudioManager.STREAM_MUSIC,0)
    }

    private fun createNewSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(15)
            .build()
    }

    override fun onStop() {
        super.onStop()
        sp?.release()
        sp = null
    }
}