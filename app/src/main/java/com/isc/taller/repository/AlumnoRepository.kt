package com.isc.taller.repository

import androidx.lifecycle.LiveData
import com.isc.taller.data.AlumnoDao
import com.isc.taller.model.Alumno

class AlumnoRepository(private val alumnoDao: AlumnoDao) {
    val getAllData: LiveData<List<Alumno>> = alumnoDao.getAllData()

    suspend fun addAlumno(alumno: Alumno){
        alumnoDao.addAlumno(alumno)

    }
}