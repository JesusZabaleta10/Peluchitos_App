package com.example.peluchitos_app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Peluchito(
    var id: String,
    var nombre: String,
    var cantidad: String,
    var precio: String
) : Parcelable
