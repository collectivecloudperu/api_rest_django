package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Datos(

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("precio")
    val precio: String,

    @SerializedName("stock")
    val stock: String,

    @SerializedName("img")
    val img: String,

)