package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.helloworld.databinding.ActivityCatatanBinding
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class catatan : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCatatanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNew.setOnClickListener(this)
        binding.buttonOpen.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_new -> newFile()
            R.id.button_open -> showList()
            R.id.button_save -> saveFile()
        }
    }

    private fun newFile() {
        binding.editTitle.setText("")
        binding.editFile.setText("")
        Toast.makeText(this, "Membersihkan file", Toast.LENGTH_SHORT).show()
    }

    private fun showList() {
        val arrayList = ArrayList<String>()
        val path: File = filesDir
        Collections.addAll(arrayList, *path.list() as Array<String>)
        val items = arrayList.toTypedArray<CharSequence>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih file yang di inginkan")
        builder.setItems(items) { dialog, item -> loadData(items[item].toString()) }
        val alert = builder.create()
        alert.show()
    }

    private fun loadData(title: String) {
        val fileModel = FileHelper.readFromFile(this, title)
        binding.editTitle.setText(fileModel.filename)
        binding.editFile.setText(fileModel.data)
        Toast.makeText(this, "Proses " + fileModel.filename + " data", Toast.LENGTH_SHORT).show()
    }

    private fun saveFile() {
        when {
            binding.editTitle.text.toString().isEmpty() -> Toast.makeText(
                this,
                "Nama file harus di isi",
                Toast.LENGTH_SHORT
            ).show()
            binding.editFile.text.toString().isEmpty() -> Toast.makeText(
                this,
                "Isi catatan harus di isi",
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                val title = binding.editTitle.text.toString()
                val text = binding.editFile.text.toString()
                val fileModel = FileModel()
                fileModel.filename = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this)
                Toast.makeText(this, "Menyimpan " + fileModel.filename + " file", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}