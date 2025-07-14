package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class verSuperheroe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_superheroe, container, false)

        view.findViewById<Button>(R.id.btn_ver_superheroe_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verSuperheroe_to_listaSuperheroe)
        }

        view.findViewById<Button>(R.id.btn_ver_superheroe_eliminar).setOnClickListener {
            lifecycleScope.launch {
                ApiService.getApiMamager()
                    .deleteSuperheroes(arguments?.getInt("id")?:0)
                findNavController().navigate(R.id.action_verSuperheroe_to_listaSuperheroe)
            }
        }

        view.findViewById<Button>(R.id.btn_ver_superheroe_editar).setOnClickListener {
            val id = arguments?.getInt("id")?:0
            var bundle= Bundle()
                .apply {
                    putInt("id", id)
                }
            findNavController().navigate(R.id.action_verSuperheroe_to_editarSuperheroe, bundle)
        }

        lifecycleScope.launch {
            var superheroe = ApiService.getApiMamager().getSuperheroes(
                arguments?.getInt("id")?:0)

            view.findViewById<TextView>(R.id.tv_ver_superheroe_id)
                .text="ID: "+superheroe.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_superheroe_nombre)
                .text="Nombre: "+superheroe.nombre.toString()

            view.findViewById<TextView>(R.id.tv_ver_superheroe_universo)
                .text="Universo: "+superheroe.universo.toString()

            view.findViewById<TextView>(R.id.tv_ver_superheroe_poder)
                .text="Poder Principal: "+superheroe.poder_principal.toString()

            view.findViewById<TextView>(R.id.tv_ver_superheroe_fuerza)
                .text="Nivel de Fuerza: "+superheroe.nivel_de_fuerza.toString()

            view.findViewById<TextView>(R.id.tv_ver_superheroe_descripcion)
                .text="Descripcion: "+superheroe.descripcion.toString()
        }
        return view
    }

}