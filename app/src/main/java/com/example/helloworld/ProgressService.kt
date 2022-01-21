package com.example.helloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.SoundPool
import android.widget.Toast
import androidx.core.app.JobIntentService

private var sp : SoundPool? = null
private var soundID = 0

class ProgressService : JobIntentService(){
    override fun onHandleWork(intent: Intent) {
        var olahragaProgress = 0
        do{
            Thread.sleep(1000)
            olahragaProgress+=10
            var intentProgress = Intent(ACTION_PROGRESS)
            intentProgress.putExtra(EXTRA_PERSEN,olahragaProgress)
            intentProgress.putExtra(EXTRA_FINISH,false)
            if (olahragaProgress>=100)
                intentProgress.putExtra(EXTRA_FINISH,true)
            sendBroadcast(intentProgress)
        }while (olahragaProgress<100)
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Lakukan Gerakan",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Gerakan Selesai",Toast.LENGTH_SHORT).show()
    }

    companion object{
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, ProgressService::class.java,
            JOB_ID_PROGRESS,intent)
        }
    }
}