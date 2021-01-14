package es.utad.videoclub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import es.utad.videoclub.R
import es.utad.videoclub.database.Pelicula

class FichaPeliculaFragment : Fragment(){
    var textViewTitulo: TextView? = null
    var textViewTipo: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragmento_ficha, container, false)
        textViewTitulo = v.findViewById(R.id.textViewFichaTitulo)
        textViewTipo = v.findViewById(R.id.textViewFichaTipo)
        return v
    }

    fun updateData(pelicula: Pelicula?) {
        if (pelicula!=null) {
            textViewTitulo!!.text = pelicula.titulo
            textViewTipo!!.text = pelicula.tipo
        }
    }
}