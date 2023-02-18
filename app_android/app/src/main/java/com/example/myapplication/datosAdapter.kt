package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class datosAdapter(private var mLista: List<Datos>) : RecyclerView.Adapter<datosAdapter.DatosViewHolder>() {

    class DatosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(property: Datos) {

            val nombre = itemView.findViewById<TextView>(R.id.nombre)
            val precio = itemView.findViewById<TextView>(R.id.precio)
            val stock = itemView.findViewById<TextView>(R.id.stock)
            val img = itemView.findViewById<ImageView>(R.id.img)

            nombre.text = property.nombre
            precio.text = property.precio
            stock.text = property.stock
            Glide.with(itemView.context).load(property.img).centerCrop().into(img)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return DatosViewHolder(v)

    }

    override fun getItemCount(): Int {

        return mLista.size

    }

    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {

        holder.bind(mLista[position])

    }


}