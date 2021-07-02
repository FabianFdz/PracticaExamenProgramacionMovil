package com.isc.taller.ui.alumno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.isc.taller.R
import com.isc.taller.databinding.FragmentAddAlumnoBinding
import com.isc.taller.model.Alumno
import com.isc.taller.viewmodel.AlumnoViewModel

class AddAlumnoFragment : Fragment() {
    private var _binding: FragmentAddAlumnoBinding? = null
    private val binding get() = _binding!!
    private  lateinit var alumnoViewModel: AlumnoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddAlumnoBinding.inflate(inflater,container,false)
        val root:View = binding.root

        alumnoViewModel = ViewModelProvider(this).get(AlumnoViewModel::class.java)

        binding.button.setOnClickListener { insertaPersona() }
        return root
    }

    private fun insertaPersona() {
        val carnet = binding.etCarnet.text.toString()
        val nombre = binding.etNombre.text.toString()
        val apellidos = binding.etApellidos.text.toString()
        val edad = binding.etEdad.text.toString()
        val correo = binding.etCorreoElectronico.text.toString()
        if(validos(carnet,nombre,apellidos,edad,correo)){
            val alumno = Alumno(0,carnet,nombre,apellidos,Integer.parseInt(edad),correo)
            alumnoViewModel.addAlumno(alumno)
            Toast.makeText(requireContext(),getString(R.string.alumnoadd),
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addPersonaFragment_to_listAlumnosFragment)



        }else{
            Toast.makeText(requireContext(),getString(R.string.alumnodatos),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun validos(carnet: String, nombre: String, apellidos: String, edad: String, correo: String): Boolean {
        return !(carnet.isEmpty()||nombre.isEmpty()||apellidos.isEmpty()||edad.isEmpty()||correo.isEmpty())

    }


}