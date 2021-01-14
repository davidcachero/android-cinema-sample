package es.utad.videoclub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import es.utad.videoclub.activities.CatalogoActivity
import es.utad.videoclub.database.DataRepository
import es.utad.videoclub.utils.JsonReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dataRepository = DataRepository(this)
        var tipos = dataRepository.getTipos()

        if (tipos.isEmpty()){
            var jsonReader = JsonReader(resources)
            //var listaPeliculas = jsonReader.readPeliculas()
            var listaPeliculas = jsonReader.readPeliculasGson()
            dataRepository.insert(listaPeliculas)
        }
    }

    fun onVerCatalogo(view: View) {
        var intent = Intent(this, CatalogoActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Atención: ")
            builder.setMessage("¿Desea salir de la aplicación?")

            builder.setPositiveButton("SI"){ dialog, _ -> finish()}
            builder.setNegativeButton("NO"){ dialog, which ->  }
            builder.show()
    }
}