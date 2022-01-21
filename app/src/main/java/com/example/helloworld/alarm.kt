package com.example.helloworld

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_alarm.*
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class alarm : AppCompatActivity() {

    private var cal = Calendar.getInstance()

    private fun setMyTimeFormat() :String{
        val myFormat = "HH:mm"
        val sdf = SimpleDateFormat(myFormat)
        return sdf.format(cal.time)
    }

    private fun myTimePicker() : TimePickerDialog.OnTimeSetListener{
        val timeListener = object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                cal.set(Calendar.HOUR_OF_DAY,p1)
                cal.set(Calendar.MINUTE,p2)
                timeAlarm.text = setMyTimeFormat()
            }
        }
        return timeListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        var mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val requestCode = 101
        var mPendingIntent: PendingIntent? = null
        var sendIntent: Intent? = null
        switch1.setOnCheckedChangeListener { buttonview, isChecked ->
            if (isChecked) {
                if (mPendingIntent != null) {
                    mAlarmManager.cancel(mPendingIntent)
                    mPendingIntent?.cancel()
                }
                var setAlarmTime = Calendar.getInstance()
                var time = timeAlarm.text.split(":")
                setAlarmTime.set(Calendar.HOUR_OF_DAY, time[0].toInt())
                setAlarmTime.set(Calendar.MINUTE, time[1].toInt())
                setAlarmTime.set(Calendar.SECOND, 0)

                sendIntent = Intent(this, myAlarmReceiver::class.java)
                sendIntent?.putExtra(EXTRA_PESAN, myMessage.text.toString())
                mPendingIntent = PendingIntent.getBroadcast(this, requestCode, sendIntent!!, 0)
                mAlarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    setAlarmTime.timeInMillis,
                    mPendingIntent
                )
                Toast.makeText(
                    this,
                    "Alarm di setel untuk jam ${timeAlarm.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                if(mPendingIntent!=null){
                    mAlarmManager.cancel(mPendingIntent)
                    mPendingIntent?.cancel()
                    Toast.makeText(this,"Alarm dibatalkan",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        showTimePicker.setOnClickListener { TimePickerDialog(
            this,myTimePicker(),
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show() }
    }
}