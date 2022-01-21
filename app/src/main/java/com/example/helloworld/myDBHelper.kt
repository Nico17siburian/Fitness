package com.example.helloworld

import MyDB.makanDB
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class myDBHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAMA, null, DATABASE_VERSION
) {

    companion object {
        private val DATABASE_NAMA = "mysqlitedb.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_MAKAN_TABEL: String = "CREATE TABLE  ${makanDB.makanTabel.TABEL_MAKAN} " +
                "(${makanDB.makanTabel.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${makanDB.makanTabel.COLUMN_NAMA} TEXT," +
                "${makanDB.makanTabel.COLUMN_LEMAK} INTEGER," +
                "${makanDB.makanTabel.COLUMN_KARBO} INTEGER," +
                "${makanDB.makanTabel.COLUMN_PROTEIN} INTEGER," +
                "${makanDB.makanTabel.COLUMN_ENERGI} INTEGER)"
        db?.execSQL(CREATE_MAKAN_TABEL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${makanDB.makanTabel.TABEL_MAKAN}")
        onCreate(db)
    }

    fun addMakan(makan: Makan): Long {
        var db: SQLiteDatabase = this.writableDatabase
        val contentValues: ContentValues = ContentValues().apply {
            put(makanDB.makanTabel.COLUMN_NAMA, makan.nama)
            put(makanDB.makanTabel.COLUMN_LEMAK, makan.lemak)
            put(makanDB.makanTabel.COLUMN_KARBO, makan.karbo)
            put(makanDB.makanTabel.COLUMN_PROTEIN, makan.protein)
            put(makanDB.makanTabel.COLUMN_ENERGI, makan.energi)
        }
        var success :Long = db.insert(makanDB.makanTabel.TABEL_MAKAN, null, contentValues)
        db.close()
        return success
    }

    fun viewAllNama(): List<String> {
        val namaList = ArrayList<String>()
        val SELECT_NAMA : String = "SELECT ${makanDB.makanTabel.COLUMN_NAMA} " +
                "FROM ${makanDB.makanTabel.TABEL_MAKAN}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(SELECT_NAMA, null)
        } catch (e : SQLException){
            return ArrayList()
        }
        var makanNama : String = ""
        if(cursor.moveToFirst()) {
            do{
                makanNama = cursor.getString(cursor.getColumnIndex(makanDB.makanTabel.COLUMN_NAMA))
                namaList.add(makanNama)
            }while (cursor.moveToNext())
        }
        return namaList
    }

    fun deleteMakan(nama : String){
        var db = this.writableDatabase
        var selection = "${makanDB.makanTabel.COLUMN_NAMA} = ?"
        var selectionArgs = arrayOf(nama)
        db.delete(makanDB.makanTabel.TABEL_MAKAN, selection, selectionArgs)
    }
}