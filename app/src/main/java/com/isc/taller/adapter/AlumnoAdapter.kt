package com.isc.taller.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.isc.taller.databinding.AlumnoFilaBinding
import com.isc.taller.databinding.FragmentAddAlumnoBinding
import com.isc.taller.model.Alumno

class AlumnoAdapter: RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder>() {

    private var ListaAlumno = emptyList<Alumno>()

   inner class AlumnoViewHolder(private val itemBinding: AlumnoFilaBinding):
   RecyclerView.ViewHolder(itemBinding.root){
        fun bind(alumno: Alumno){
            itemBinding.tvId01.text=alumno.id.toString()
            itemBinding.tvNombre01.text=alumno.nombre.toString()
            itemBinding.tvApellidos01.text=alumno.apellidos.toString()
            itemBinding.tvCarnet01.text=alumno.carnet.toString()
            itemBinding.tvEdad01.text=alumno.edad.toString()
            itemBinding.tvCorreo01.text= "("+alumno.correo.toString()+")"

        }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val itemBinding = AlumnoFilaBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
        return AlumnoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumnoActual = ListaAlumno[position]
        holder.bind(alumnoActual)

    }

    override fun getItemCount(): Int {
       return ListaAlumno.size
    }

    fun setData(alumno: List<Alumno>){
        this.ListaAlumno=alumno
        notifyDataSetChanged()
    }
}