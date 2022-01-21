package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Olahraga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olahraga)
    }
    fun next(view: View){
        var intentReply = Intent(this, crunch::class.java)
        startActivity(intentReply)
    }

    fun kembali(view: View){
        var intentReply = Intent(this, MainActivity::class.java)
        startActivity(intentReply)
    }
}