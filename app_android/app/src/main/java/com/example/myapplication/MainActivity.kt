package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Variables para cargar el Adapter y RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        obtenerToken()

        // Mostramos los datos en el RecyclerView
        manager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerview)

    }

    fun obtenerToken() {

        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTY3NjgwOTYwMSwiaWF0IjoxNjc2NzIzMjAxLCJqdGkiOiI5OWZjZGJmNjhmNzk0ODAwYTU5NWJkYjIzMjUzOWYyZiIsInVzZXJfaWQiOjF9.nGw0CHVI0BgqZu9mFkfxd7rCCdU_pdE-VAqHa59hjvI"

        Servicio.instancia.obtToken(token)
            .enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {

                    val message = response.body()?.access
                    Log.e("respuesta", message.toString())

                    // Guardamos el token obtenido en Shared Preferences
                    val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
                    val editor = sp.edit()
                    editor.putString("access", message.toString())
                    editor.apply()

                    obtenerDatos()


                }

                // Si hay error al obtener los datos, mostramos un mensaje
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.e("Estado", "Hubo Error al solicitar datos", t)
                }

            })

    }

    private fun obtenerDatos() {

        // Leemos el token almacenado en Shared Preferences
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = sp.getString("access", "")

        val datos = Servicio.instancia.listarDatos("Bearer $token", q = "all", nombre = String())
        datos.enqueue(object : Callback<List<Datos>?> {

            override fun onResponse(call : Call<List<Datos>?>, response: Response<List<Datos>?>) {
                //val data = response.body()?.get(0)?.nombre
                val data = response.body()
                Log.e("fresa1", data.toString())

                recyclerView = findViewById<RecyclerView>(R.id.recyclerview).apply{
                    myAdapter = datosAdapter(response.body()!!)
                    layoutManager = manager
                    recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
                    adapter = myAdapter

                }

            }

            // Si hay error al obtener los datos, mostramos un mensaje
            override fun onFailure(call: Call<List<Datos>?>, t: Throwable) {
                Log.e("fresa2", "Hubo Error al solicitar datos", t)
            }

        })

    }


}