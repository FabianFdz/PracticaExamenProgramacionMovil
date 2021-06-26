package com.isc.taller.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.isc.taller.data.PersonaDatabase
import com.isc.taller.model.Persona
import com.isc.taller.repository.PersonaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaViewModel(application: Application)
    : AndroidViewModel(application) {
    val getAllData: LiveData<List<Persona>>
    private val repository: PersonaRepository

    init{
        val personaDao= PersonaDatabase.getDatabase(application).personaDao()
        repository = PersonaRepository(personaDao)
        getAllData = repository.getAllData
    }

    fun addPersona(persona: Persona) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPersona(persona)
        }
    }

    fun deletePersona(persona: Persona) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePersona(persona)
        }
    }

    fun updatePersona(persona: Persona) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePersona(persona)
        }
    }
}