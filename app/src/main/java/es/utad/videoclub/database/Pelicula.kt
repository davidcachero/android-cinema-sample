package es.utad.videoclub.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pelicula (
    @PrimaryKey val codigo: Int,
    val titulo: String?,
    val tipo: String?
)
