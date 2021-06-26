package com.isc.taller.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.isc.taller.model.Persona

@Dao
interface PersonaDao {
    @Query("SELECT * FROM PERSONA")
    fun getAllData() : LiveData<List<Persona>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPersona(persona: Persona)

    @Update
    suspend fun updatePersona(persona: Persona)

    @Delete
    suspend fun deletePersona(persona: Persona)

}