package com.isc.taller.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumno")
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="carnet")
    val carnet:String?,
    @ColumnInfo(name="nombre")
    val nombre:String?,
    @ColumnInfo(name="apellidos")
    val apellidos:String?,
    @ColumnInfo(name="edad")
    val edad: Int,
    @ColumnInfo(name="correo")
    val correo:String?

)
