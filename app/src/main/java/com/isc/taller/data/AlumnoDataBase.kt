package com.isc.taller.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isc.taller.model.Alumno

@Database(entities = [Alumno::class],version = 1,exportSchema = false
)
abstract class AlumnoDataBase: RoomDatabase() {
    abstract fun alumnoDao(): AlumnoDao

    companion object{
        @Volatile
        private var INSTANCE: AlumnoDataBase? = null
                fun getDatabase(context: android.content.Context): AlumnoDataBase{
                    val tempInstance = INSTANCE
                    if(tempInstance != null){
                        return tempInstance
                    }
                    synchronized(this){
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            AlumnoDataBase::class.java,
                            "alumno_database"
                        ).build()
                        INSTANCE = instance
                        return instance
                    }
                }
    }
}