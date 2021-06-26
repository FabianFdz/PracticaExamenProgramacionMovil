package com.isc.taller.repository

import androidx.lifecycle.LiveData
import com.isc.taller.data.PersonaDao
import com.isc.taller.model.Persona

class PersonaRepository(private val personaDao: PersonaDao) {

    val getAllData: LiveData<List<Persona>> = personaDao.getAllData()

    suspend fun addPersona(persona: Persona) {
        personaDao.addPersona(persona)
    }

    suspend fun updatePersona(persona: Persona) {
        personaDao.updatePersona(persona)
    }

    suspend fun deletePersona(persona: Persona) {
        personaDao.deletePersona(persona)
    }
}