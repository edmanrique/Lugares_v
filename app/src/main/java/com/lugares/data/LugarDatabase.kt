package com.lugares.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.lugares.model.Lugar

@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class LugarDatabase: RoomDatabase() {
    abstract fun lugarDao() : LugarDao

    companion object{
        @Volatile
        private var INSTANCE: LugarDatabase? = null

        fun getDatabase(context: android.content.Context) : LugarDatabase{
            val instance = INSTANCE
            if(instance != null){
                return instance
            }
            synchronized(this){
                val basedatos = Room.databaseBuilder(
                    context.applicationContext,
                    LugarDatabase::class.java,
                    "lugar_database"
                ).build()
                INSTANCE = basedatos
                return basedatos
            }
        }
    }
}