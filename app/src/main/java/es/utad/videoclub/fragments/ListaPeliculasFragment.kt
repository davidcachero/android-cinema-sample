package es.utad.videoclub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.utad.videoclub.R
import es.utad.videoclub.adapters.PeliculasAdapter
import es.utad.videoclub.database.DataRepository
import es.utad.videoclub.database.Pelicula

class ListaPeliculasFragment(): Fragment() {

    var activityListener: View.OnClickListener? = null
    var peliculaSeleccionada: Pelicula? = null
    lateinit var recyclerViewLista: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragmento_lista, container, false)
        recyclerViewLista = v.findViewById<RecyclerView>(R.id.recyclerviewlista)
        var spinnerTipo = v.findViewById<Spinner>(R.id.spinnerTipo)
        var dataRepository = DataRepository(requireContext())
        var tipos = dataRepository.getTipos()
        var tipoAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tipos)
        spinnerTipo.adapter = tipoAdapter

        spinnerTipo.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                            rellenarListaPeliculas(tipos[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // write code to perform some action
            }
        }
        return v
    }

    fun rellenarListaPeliculas(tipo:String){
        var dataRepository = DataRepository(requireContext())
        var peliculas = dataRepository.getPeliculas(tipo)

        val adapter = PeliculasAdapter(peliculas) { pelicula ->
            peliculaSeleccionada = pelicula
            if (activityListener != null) {
                activityListener!!.onClick(view)
            }
        }
        recyclerViewLista.setAdapter(adapter)
        recyclerViewLista.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false))
    }

}