package com.example.repository

import android.content.Context
import com.example.database.NotasDatabase
import com.example.entities.Notas
import com.example.notas.dao.NotasDao
import kotlinx.coroutines.flow.Flow

class NotasRepository(private val notasDao: NotasDao)  {
    companion object {
        private var INSTANCE : NotasRepository? = null
        fun getRepository(context: Context) : NotasRepository {
            return INSTANCE ?: synchronized(this) {
                val database = NotasDatabase.getDatabase(context)
                val instance = NotasRepository(database.notasDao())
                INSTANCE = instance
                instance
            }
        }
    }

    val allNotas: Flow<List<Notas>> = notasDao.getAlphabetizedProducts()

    suspend fun insert(notas: Notas) {
        notasDao.insert(notas)
    }
    suspend fun deleteOneItem(Id:Int) {
        notasDao.deleteOneItem(Id)
    }

}