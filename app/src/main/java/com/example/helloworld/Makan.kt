package com.example.helloworld

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Makan : Parcelable {
    var id : Int = 0
    var nama : String = ""
    var lemak : Int = 0
    var karbo : Int = 0
    var protein : Int = 0
    var energi : Int = 0
}