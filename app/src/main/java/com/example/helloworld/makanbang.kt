package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_makanbang.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class makanbang : AppCompatActivity() {
    var mySQLitedb : myDBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makanbang)
        mySQLitedb = myDBHelper(this)
        updateAdapter()

        btn_add.setOnClickListener{
            var makanTmp = Makan()
            makanTmp.nama = mkn_nama.text.toString()
            makanTmp.lemak = mkn_lemak.text.toString().toInt()
            makanTmp.karbo = mkn_karbo.text.toString().toInt()
            makanTmp.protein = mkn_protein.text.toString().toInt()
            makanTmp.energi = mkn_energi.text.toString().toInt()
            var result = mySQLitedb?.addMakan(makanTmp)
            if (result!=-1L){
                Toast.makeText(this,"Berhasil", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show()
            }
            updateAdapter()
            mkn_nama.text.clear()
            mkn_lemak.text.clear()
            mkn_karbo.text.clear()
            mkn_protein.text.clear()
            mkn_energi.text.clear()
        }
        btn_del.setOnClickListener{
            var nama = jenisMakanan.selectedItem.toString()
            if(nama != null || nama != ""){
                doAsync {
                    mySQLitedb?.deleteMakan(nama)
                    updateAdapter()
                }
            }
        }

//        val adapter = ArrayAdapter.createFromResource(this, R.array.item_makanan, android.R.layout.simple_spinner_dropdown_item)
//        jenisMakanan.adapter = adapter
    }
    fun updateAdapter(){
        doAsync {
            var namaList = mySQLitedb?.viewAllNama()?.toTypedArray()
            uiThread {
                if(jenisMakanan != null && namaList?.size != 0){
                    var arrayAdapter = ArrayAdapter(applicationContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        namaList!!)
                    jenisMakanan.adapter = arrayAdapter
                }
            }
        }
    }
}