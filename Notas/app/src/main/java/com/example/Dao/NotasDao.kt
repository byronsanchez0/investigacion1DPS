package com.example.notas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entities.Notas
import kotlinx.coroutines.flow.Flow

@Dao
interface NotasDao {
    @Query("SELECT * FROM product_table ORDER BY titulo ASC")
    fun getAlphabetizedProducts(): Flow<List<Notas>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notas: Notas)
    @Query("DELETE FROM  product_table WHERE id=:id")
    suspend fun deleteOneItem(id:Int)

}