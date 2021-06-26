package com.isc.taller.ui.persona

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.isc.taller.R
import com.isc.taller.databinding.FragmentUpdate2PersonaBinding
import com.isc.taller.databinding.FragmentUpdatePersonaBinding
import com.isc.taller.model.Persona
import com.isc.taller.viewmodel.PersonaViewModel

class Update2Persona : Fragment() {
    private var _binding: FragmentUpdate2PersonaBinding?=null
    private val binding get() = _binding!!

    //Para leer el argumento...
    private val args by navArgs<Update2PersonaArgs>()

    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdate2PersonaBinding.inflate(inflater,
            container,false)
        val root: View = binding.root

        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)

        binding.etCedula.setText(args.personaActual.cedula)
        binding.etNombre.setText(args.personaActual.nombre)
        binding.etApellidos.setText(args.personaActual.apellidos)
        binding.etEdad.setText(args.personaActual.edad.toString())

        binding.btModificar.setOnClickListener { modificarPersona() }

        setHasOptionsMenu(true)   //Este fragmente tiene unas opciones de menu...
        return root
    }

    //Se incluye la opción del eliminar en el menú...
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Si se selecciona el ícono de eliminar...
        if (item.itemId == R.id.menu_delete) {
            deletePersona()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePersona() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delete_persona)+
                " ${args.personaActual.cedula}")
        builder.setMessage(getString(R.string.delete_persona_q)+
        " ${args.personaActual.nombre} ${args.personaActual.apellidos}?")

        builder.setPositiveButton("Si") {_,_ ->
            personaViewModel.deletePersona(args.personaActual)
            Toast.makeText(requireContext(),
                getString(R.string.deleted_persona),
                Toast.LENGTH_SHORT)
            findNavController().navigate(R.id.action_nav_Update2Persona_to_nav_ListPersona)
        }
        builder.setNegativeButton("No") {_,_->}
        builder.create().show()
    }


    private fun modificarPersona() {
        val cedula=binding.etCedula.text.toString()
        val nombre=binding.etNombre.text.toString()
        val apellidos=binding.etApellidos.text.toString()
        val edad=binding.etEdad.text.toString()
        if (validos(cedula,nombre,apellidos,edad)) {
            val persona = Persona(args.personaActual.id,cedula,nombre,apellidos,edad.toInt())
            personaViewModel.updatePersona(persona)
            Toast.makeText(requireContext(),getString(R.string.personaupdate),
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_nav_Update2Persona_to_nav_ListPersona)
        } else {
            Toast.makeText(requireContext(),getString(R.string.personafail),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun validos(cedula: String, nombre: String, apellidos: String, edad: String): Boolean {
        return !(cedula.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || edad.isEmpty())
    }

}