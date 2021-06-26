package com.isc.taller.ui.persona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.isc.taller.R
import com.isc.taller.databinding.FragmentListPersonaBinding
import com.isc.taller.databinding.FragmentUpdatePersonaBinding
import com.isc.taller.model.Persona
import com.isc.taller.viewmodel.PersonaViewModel

class UpdatePersona : Fragment() {
    private var _binding: FragmentUpdatePersonaBinding?=null
    private val binding get() = _binding!!

    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdatePersonaBinding.inflate(inflater,
            container,false)
        val root: View = binding.root

        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)

        binding.btAgregar.setOnClickListener { insertaPersona() }

        return root
    }

    private fun insertaPersona() {
        val cedula=binding.etCedula.text.toString()
        val nombre=binding.etNombre.text.toString()
        val apellidos=binding.etApellidos.text.toString()
        val edad=binding.etEdad.text.toString()
        if (validos(cedula,nombre,apellidos,edad)) {
            val persona = Persona(0,cedula,nombre,apellidos,edad.toInt())
            personaViewModel.addPersona(persona)
            Toast.makeText(requireContext(),getString(R.string.personaadd),
            Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_nav_UpdatePersona_to_nav_ListPersona)
        } else {
            Toast.makeText(requireContext(),getString(R.string.personafail),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun validos(cedula: String, nombre: String, apellidos: String, edad: String): Boolean {
        return !(cedula.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || edad.isEmpty())
    }
}