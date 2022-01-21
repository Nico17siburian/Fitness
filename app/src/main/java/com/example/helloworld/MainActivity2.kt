package com.example.helloworld

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val nama = preferences.getString("NAMA","")
        username.text = nama

        logout.setOnClickListener{
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            var intentReply = Intent(this, login::class.java)
            startActivity(intentReply)
            finish()
        }

        var AirplaneReceiver = MyAirplaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirplaneReceiver,filter)
    }
    fun onBmiButton(view: View) {
        var intentReply = Intent(this, Inputbmi::class.java)
        startActivity(intentReply)
    }

    fun onHistoryButton(view: View) {
        var intentReply = Intent(this, MainActivity::class.java)
        startActivity(intentReply)
    }
    fun onWaterButton(view: View){
        var intentReply = Intent(this, WaterIntake::class.java)
        startActivity(intentReply)
    }
    fun onEatButton(view: View){
        var intentReply = Intent(this, makanbang::class.java)
        startActivity(intentReply)
    }

    fun onGymButton(view: View){
        var intentReply = Intent(this, Olahraga::class.java)
        startActivity(intentReply)
    }
}