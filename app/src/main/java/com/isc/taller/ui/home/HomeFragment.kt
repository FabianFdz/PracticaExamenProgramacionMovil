package com.isc.taller.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.isc.taller.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //Referencia a un elemento dedentro de Realtime Database
    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Se inicializa la referencia a un elemento llamado numero en Realtime Database
        myRef = Firebase.database.getReference("resultSum")

        //Asocio un "visor" de actualizaciones de myRef...
        myRef.addValueEventListener(object:ValueEventListener{
            //Cuando se cambia un valor recibo el cambio y puedo hacer la actualización
            override fun onDataChange(snapshot: DataSnapshot) {
                val valor = snapshot.getValue<Int>()
                binding.textResult.text = valor.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                binding.textResult.text = "Error realTime"
            }
        })

        //Se define el onClick del boton para sumar...
        binding.btSum.setOnClickListener { sum() }

        return root
    }

    private fun sum() {
        val numberOne = binding.numberOne.text.toString().toDouble()
        val numberTwo = binding.numberTwo.text.toString().toDouble()
        val result = numberOne + numberTwo
        myRef.setValue(result)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}