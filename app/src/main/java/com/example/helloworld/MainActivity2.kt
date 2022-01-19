package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    fun onBmiButton(view: View) {
        var intentReply = Intent(this, Inputbmi::class.java)
        startActivity(intentReply)
    }

    fun onHistoryButton(view: View) {
        finish()
    }
    fun onWaterButton(view: View){
        var intentReply = Intent(this, WaterIntake::class.java)
        startActivity(intentReply)
    }
}