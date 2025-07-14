package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class listaSuperheroe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_superheroe, container, false)

        val rv_lista_superheroe=view.findViewById<RecyclerView>(R.id.rv_lista_superheroe)

        view.findViewById<Button>(R.id.btn_nuevo_superheroe).setOnClickListener {
            findNavController().navigate(R.id.action_listaSuperheroe_to_crearSuperheroe)
        }

        lifecycleScope.launch {
            try {
                val superheroe = ApiService
                    .getApiMamager()
                    .getSuperheroes()
                rv_lista_superheroe.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_superheroe.adapter = lista_superheroe_adapter(superheroe)
            }catch (e: Exception){
                var message="Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Superheroes")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
        return view
    }

}