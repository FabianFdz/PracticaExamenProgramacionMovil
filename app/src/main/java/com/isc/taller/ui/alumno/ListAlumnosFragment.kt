package com.isc.taller.ui.alumno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.isc.taller.R
import com.isc.taller.databinding.FragmentListAlumnosBinding
import com.isc.taller.viewmodel.AlumnoViewModel

class ListAlumnosFragment : Fragment() {
    private var _binding: FragmentListAlumnosBinding? = null
    private val binding get() = _binding!!

    private lateinit var alumnoViewModel: AlumnoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListAlumnosBinding.inflate(inflater,container,false)
        val root:View = binding.root

        //val personaAparter = Pers
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listAlumnosFragment_to_addPersonaFragment)
        }
        return root
    }


}