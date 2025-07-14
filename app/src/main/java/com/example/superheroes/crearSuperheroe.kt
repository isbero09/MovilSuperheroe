package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class crearSuperheroe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_superheroe, container, false)


        view.findViewById<Button>(R.id.btn_add_superheroe).setOnClickListener {

            val nombre = view.findViewById<EditText>(R.id.superheroe_nombre_nuevo)
                .text.toString().trim()

            val universo = view.findViewById<EditText>(R.id.superheroe_nuevo_universo)
                .text.toString().trim()

            val poder = view.findViewById<EditText>(R.id.superheroe_nuevo_poder)
                .text.toString().trim()

            val fuerza = view.findViewById<EditText>(R.id.superheroe_nuevo_fuerza)
                .text.toString().trim()

            val descripcion = view.findViewById<EditText>(R.id.superheroe_nuevo_descripcion)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    ApiService.getApiMamager().postSuperheroes(
                        Superheroe(0, nombre, universo, poder, fuerza, descripcion)
                    )
                    Toast.makeText(
                        context, "Superheroe resgistrado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                }catch (e: Exception){
                    Toast.makeText(
                        context,"Error al registrar superheroe: ${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return view
    }
}