package com.isc.taller.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.isc.taller.databinding.PersonaFilaBinding
import com.isc.taller.model.Persona
import com.isc.taller.ui.persona.ListPersonaDirections

class PersonaAdapter: RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {
    //Se define la lista que se usa para contener la información de las personas (de la tabla)
    private var listaPersonas = emptyList<Persona>()

    //Esta clase interna se usa para "dibujar" la información de una persona
    //En una "caja" para cada fila de la tabla...
    inner class PersonaViewHolder(private val itemBinding: PersonaFilaBinding):
            RecyclerView.ViewHolder(itemBinding.root) {
                fun bind(persona: Persona) {
                    itemBinding.tvId.text = persona.id.toString()
                    itemBinding.tvNombre.text = persona.nombre
                    itemBinding.tvApellidos.text = persona.apellidos
                    itemBinding.tvCedula.text = persona.cedula
                    itemBinding.tvEdad.text = "("+persona.edad.toString()+")"
                    itemBinding.vistaFila.setOnClickListener {
val action = ListPersonaDirections.actionNavListPersonaToNavUpdate2Persona(persona)
                        itemView.findNavController().navigate(action)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val itemBinding = PersonaFilaBinding
            .inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return PersonaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val personaActual = listaPersonas[position]
        holder.bind(personaActual)
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

    fun setData(personas: List<Persona>) {
        this.listaPersonas=personas
        notifyDataSetChanged() //Redibuja el adapter... lo actualiza todo...
    }
}