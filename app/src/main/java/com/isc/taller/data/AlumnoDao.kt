package com.isc.taller.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isc.taller.model.Alumno

@Dao
interface AlumnoDao {
    @Query("SELECT * FROM ALUMNO")
    fun getAllData() : LiveData<List<Alumno>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlumno(alumno: Alumno)

}