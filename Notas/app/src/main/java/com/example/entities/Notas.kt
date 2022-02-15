package com.example.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "product_table")

data class Notas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,

    )

