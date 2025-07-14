package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class editarSuperheroe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_superheroe, container, false)


        lifecycleScope.launch {
            var superheroe = ApiService
                .getApiMamager()
                .getSuperheroes(arguments?.getInt("id")?:0)

            view.findViewById<TextView>(R.id.superheroe_editar_nombre)
                .text=superheroe.nombre.toString()

            view.findViewById<TextView>(R.id.superheroe_editar_universo)
                .text=superheroe.universo.toString()

            view.findViewById<TextView>(R.id.superheroe_editar_poder)
                .text=superheroe.poder_principal.toString()

            view.findViewById<TextView>(R.id.superheroe_editar_fuerza)
                .text=superheroe.nivel_de_fuerza.toString()

            view.findViewById<TextView>(R.id.superheroe_editar_descripcion)
                .text=superheroe.descripcion.toString()
        }

        view.findViewById<Button>(R.id.btn_update_superheroe).setOnClickListener {
            val nombre = view.findViewById<EditText>(R.id.superheroe_editar_nombre)
                .text.toString().trim()

            val universo = view.findViewById<EditText>(R.id.superheroe_editar_universo)
                .text.toString().trim()

            val poder = view.findViewById<EditText>(R.id.superheroe_editar_poder)
                .text.toString().trim()

            val fuerza = view.findViewById<EditText>(R.id.superheroe_editar_fuerza)
                .text.toString().trim()

            val descripcion = view.findViewById<EditText>(R.id.superheroe_editar_descripcion)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    ApiService.getApiMamager()
                        .putSuperheroes(
                            Superheroe(
                                arguments?.getInt("id")?:0,
                                nombre, universo, poder, fuerza, descripcion
                            ),arguments?.getInt("id")?:0
                        )
                    Toast.makeText(
                        context, "Superheroe actulizado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarSuperheroe_to_verSuperheroe, Bundle()
                        .apply {
                            putInt("id", arguments?.getInt("id")?:0)
                    })
                } catch (e: Exception){
                    Toast.makeText(
                        context, "Error al actualizar Superheroe: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        return view
    }

}