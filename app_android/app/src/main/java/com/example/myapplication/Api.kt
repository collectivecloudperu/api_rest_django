package com.example.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// Conexión local a Django
const val URL = "http://10.0.2.2:8000/"

interface Api {

    @FormUrlEncoded
    @POST("api/token/refresh/")
    fun obtToken(
        @Field("refresh") refresh: String
    ): Call<Token>

    @GET("jugos")
    fun listarDatos(@Header("Authorization") token : String, @Query("q") q: String, @Query("nombre") nombre : String) : Call<List<Datos>>

}

object Servicio {

    val instancia: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instancia = retrofit.create(Api::class.java)
    }

}