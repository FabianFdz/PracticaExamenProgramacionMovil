package com.isc.taller.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.isc.taller.data.AlumnoDataBase
import com.isc.taller.model.Alumno
import com.isc.taller.repository.AlumnoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoViewModel(application: Application): AndroidViewModel(application) {
    val getAllData: LiveData<List<Alumno>>
    private val repository: AlumnoRepository

    init{
        val alumnoDao = AlumnoDataBase.getDatabase(application).alumnoDao()
        repository = AlumnoRepository(alumnoDao)
        getAllData = repository.getAllData
    }

    fun addAlumno(alumno: Alumno){
        viewModelScope.launch(Dispatchers.IO){
            repository.addAlumno(alumno)
        }
    }
}