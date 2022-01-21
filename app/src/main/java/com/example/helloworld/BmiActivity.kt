package com.example.helloworld

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_bmi.username
import kotlinx.android.synthetic.main.activity_inputbmi.*
import kotlinx.android.synthetic.main.activity_main.*

class BmiActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val nama = preferences.getString("NAMA","")
        if(nama == ""){
            username.text = "User"
        }else{
            username.text = nama
        }

//        var intentData = intent.data
        var intentExtra = intent
        hasilTinggi.text = "${intentExtra.getStringExtra(EXTRA_TINGGI)}"
        hasilBerat.text = "${intentExtra.getStringExtra(EXTRA_BERAT)}"
        result.text = "${intentExtra.getStringExtra(EXTRA_BMI)}"
    }

    fun onBmiButton(view: View) {
        finish()
    }
}