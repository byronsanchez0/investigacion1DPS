package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.entities.Notas
import com.example.notas.dao.NotasDao
import androidx.room.RoomDatabase

@Database(entities = [Notas::class], version = 1, exportSchema = false)
abstract class NotasDatabase : RoomDatabase(){
    abstract fun notasDao(): NotasDao
    companion object {

        @Volatile
        private var INSTANCE: NotasDatabase? = null
        fun getDatabase(context: Context): NotasDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDatabase::class.java,
                    "Nota_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}