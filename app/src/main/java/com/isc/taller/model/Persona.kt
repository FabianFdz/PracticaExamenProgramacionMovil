package com.isc.taller.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="persona")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="cedula")
    val cedula: String?,
    @ColumnInfo(name="nombre")
    val nombre: String?,
    @ColumnInfo(name="apellidos")
    val apellidos: String?,
    @ColumnInfo(name="edad")
    val edad: Int
): Parcelable
