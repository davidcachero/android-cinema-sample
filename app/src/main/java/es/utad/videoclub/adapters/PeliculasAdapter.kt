package es.utad.videoclub.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.utad.videoclub.R
import es.utad.videoclub.database.Pelicula

class PeliculasAdapter(var peliculas: List<Pelicula>, private val listener: (Pelicula) -> Unit) : RecyclerView.Adapter<PeliculasAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pelicula_item, parent, false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: PeliculasAdapter.ViewHolder, position: Int) {
        holder.bindItems(peliculas[position])
        holder.itemView.setOnClickListener { listener(peliculas[position]) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return peliculas.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(peliculas: Pelicula) {
            val textViewNombre = itemView.findViewById<TextView>(R.id.textViewTitulo)
            textViewNombre.text = peliculas.titulo
        }
    }
}