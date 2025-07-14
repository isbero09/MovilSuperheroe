package com.example.superheroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class lista_superheroe_adapter(private  var lista: List<Superheroe>): RecyclerView.Adapter<SuperheroeViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): SuperheroeViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_superheroe,parent,false)

        return SuperheroeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: SuperheroeViewHolder, position: Int) {
        val superheroe = lista[position]
        holder.nombre.text=superheroe.nombre
        holder.btn_superheroe_ver.setOnClickListener {
            val bundle = Bundle()
                .apply {
                    putInt("id",superheroe.id )
                }

            holder
                .itemView
                .findNavController()
                .navigate(R.id.action_listaSuperheroe_to_verSuperheroe,bundle)

        }
    }
}

class SuperheroeViewHolder(view: View): RecyclerView.ViewHolder(view){
    var nombre =  view.findViewById<TextView>(R.id.tv_ver_superheroe_nombre)
    var btn_superheroe_ver=  view.findViewById<Button>(R.id.btn_superheroe_ver)
}