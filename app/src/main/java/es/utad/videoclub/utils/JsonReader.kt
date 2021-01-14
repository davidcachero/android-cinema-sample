package es.utad.videoclub.utils

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.utad.videoclub.R
import es.utad.videoclub.database.Pelicula
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class JsonReader(val resources: Resources) {

    fun readPeliculas(): List<Pelicula>{

        var rdo = ArrayList<Pelicula>()

        var bufferedReader= BufferedReader(InputStreamReader(resources.openRawResource(R.raw.catalogo)))

        var json = bufferedReader.readLine()
        var jsonjObject = JSONObject(json)
        val peliculas: JSONArray = jsonjObject.getJSONArray("peliculas")
        for (i in 0 until peliculas.length()) {
            val peli = peliculas.getJSONObject(i)
            val pelicula : Pelicula = Pelicula(peli.getInt("codigo"), peli.getString("titulo"), peli.getString("tipo"))
            rdo.add(pelicula)
        }
        return rdo
    }

    fun readPeliculasGson():List<Pelicula>{
        val gson = Gson()
        var bufferedReader= BufferedReader(InputStreamReader(resources.openRawResource(R.raw.catalogo2)))

        var json = bufferedReader.readLine()

        val arrayPeliculaType = object : TypeToken<Array<Pelicula>>() {}.type

        var peliculas: Array<Pelicula> = gson.fromJson(json, arrayPeliculaType)
        return  Arrays.asList(*peliculas)
    }
}