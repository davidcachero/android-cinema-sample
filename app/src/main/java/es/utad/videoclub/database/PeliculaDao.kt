package es.utad.videoclub.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeliculaDao {

    @Query("SELECT distinct(tipo) FROM pelicula")
    fun getTipos(): List<String>

    @Query("SELECT * FROM pelicula where tipo = :tipo")
    fun getPeliculasByTipo(tipo:String): List<Pelicula>

    @Insert
    fun insert(peiculas: List<Pelicula>)
}