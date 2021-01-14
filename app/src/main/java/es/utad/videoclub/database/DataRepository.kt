package es.utad.videoclub.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val peliculaDao: PeliculaDao? = AppDatabase.getInstance(context)?.peliculaDao()

    fun insert(peliculas: List<Pelicula>):Int {
        if (peliculaDao != null){
            return InsertPeliculasAsyncTask(peliculaDao).execute(peliculas).get()
        }
        return -1
    }

    fun getTipos():List<String> {
        if (peliculaDao != null){
            return getTiposAsyncTask(peliculaDao).execute().get()
        }
        return ArrayList<String>()
    }

    fun getPeliculas(tipo: String):List<Pelicula> {
        if (peliculaDao != null){
            return getPeliculasAsyncTask(peliculaDao).execute(tipo).get()
        }
        return ArrayList<Pelicula>()
    }


    private class getTiposAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<Void, Void, List<String>>() {
        override fun doInBackground(vararg p0: Void?): List<String> {
            try {
                var tipos = peliculaDao.getTipos()
                return tipos
            }
            catch (exception: Exception){
                return ArrayList<String>()
            }
        }
    }

    private class getPeliculasAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<String, Void, List<Pelicula>>() {
        override fun doInBackground(vararg tipo: String): List<Pelicula> {
            try {
                var peliculas = peliculaDao.getPeliculasByTipo(tipo[0])
                return peliculas
            }
            catch (exception: Exception){
                return ArrayList<Pelicula>()
            }
        }
    }

    private class InsertPeliculasAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<List<Pelicula>, Void, Int>() {
        override fun doInBackground(vararg peliculas: List<Pelicula>): Int? {
            try {
                peliculaDao.insert(peliculas[0])
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }
}