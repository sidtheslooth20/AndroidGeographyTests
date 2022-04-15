package com.example.androidgeographytests.Fragments

import android.os.Parcel
import android.os.Parcelable

class DataToPassThrough(var code:Int) : Parcelable{
    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataToPassThrough> {
        override fun createFromParcel(parcel: Parcel): DataToPassThrough {
            return DataToPassThrough(parcel)
        }

        override fun newArray(size: Int): Array<DataToPassThrough?> {
            return arrayOfNulls(size)
        }
    }
}