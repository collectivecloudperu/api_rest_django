package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Token (

    @SerializedName("access")
    val access: String,

)
