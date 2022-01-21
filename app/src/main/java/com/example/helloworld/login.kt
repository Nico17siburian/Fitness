package com.example.helloworld

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    lateinit var EXTRA_NAMA : String
    lateinit var EXTRA_BERAT : String

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        if (sharedPreferences.getString("NAMA", null) != null){
            var intentReply = Intent(this, MainActivity::class.java)
            startActivity(intentReply)
            finish()
        }

        var AirplaneReceiver = MyAirplaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirplaneReceiver,filter)
    }
    fun simpan(view: View){
        val nama: String = nama.text.toString()
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("NAMA", nama)
        editor.apply()

        Toast.makeText(this, "Informasi Disimpan", Toast.LENGTH_SHORT).show()

        var intentReply = Intent(this, MainActivity::class.java)
        startActivity(intentReply)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nama",nama.text.toString())
        outState.putString("berat",berat.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        EXTRA_NAMA = savedInstanceState.getString("nama","kosong")
        EXTRA_BERAT = savedInstanceState.getString("berat","kosong")
    }
}