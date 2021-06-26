package com.isc.taller.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isc.taller.model.Persona

@Database(entities=[Persona::class], version = 1,exportSchema = false)
abstract class PersonaDatabase: RoomDatabase() {
    abstract fun personaDao(): PersonaDao
    companion object {
        @Volatile
        private var INSTANCE: PersonaDatabase? = null

        fun getDatabase(context: android.content.Context): PersonaDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonaDatabase::class.java,
                    "persona_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}