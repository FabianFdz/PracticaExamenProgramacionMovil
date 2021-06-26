package com.isc.taller.ui.persona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.isc.taller.R
import com.isc.taller.adapter.PersonaAdapter
import com.isc.taller.databinding.FragmentListPersonaBinding
import com.isc.taller.viewmodel.PersonaViewModel

class ListPersona : Fragment() {
    private var _binding: FragmentListPersonaBinding?=null
    private val binding get() = _binding!!

    //Para que se use en el adapter del recycler view
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListPersonaBinding.inflate(inflater,
        container,false)
        val root: View = binding.root

        //Activamos el recycler View (reciclador)
        val personaAdapter = PersonaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = personaAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        //Obtener la info de la tabla persona vía el personaViewModel
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)

        //Ojo cómo se define la manera de actualzar...
        personaViewModel.getAllData.observe(viewLifecycleOwner,{
            personas -> personaAdapter.setData(personas)})


        binding.btAddPersona.setOnClickListener {
            findNavController().navigate(R.id.action_nav_ListPersona_to_nav_UpdatePersona)
        }

        return root
    }
}