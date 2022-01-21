package MyDB

import android.provider.BaseColumns

object makanDB{
    class makanTabel : BaseColumns {
        companion object{
            val TABEL_MAKAN = "tbl_Makan"
            val COLUMN_ID = "Makan_Id"
            val COLUMN_NAMA = "Makan_Nama"
            val COLUMN_LEMAK = "Makan_Lemak"
            val COLUMN_KARBO = "Makan_Karbo"
            val COLUMN_PROTEIN = "Makan_Protein"
            val COLUMN_ENERGI = "Makan_Energi"
        }
    }
}