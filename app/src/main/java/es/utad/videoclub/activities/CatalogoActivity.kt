package es.utad.videoclub.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import es.utad.videoclub.R
import es.utad.videoclub.fragments.FichaPeliculaFragment
import es.utad.videoclub.fragments.ListaPeliculasFragment

class CatalogoActivity : AppCompatActivity() {

    var frameLayoutLista: FrameLayout? = null
    var frameLayoutFicha: FrameLayout? = null
    var frameLayoutFragment: FrameLayout? = null
    var listaFragment: ListaPeliculasFragment? = null
    var fichaFragment: FichaPeliculaFragment? = null
    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        frameLayoutLista = findViewById(R.id.frameLayoutLista)
        frameLayoutFicha = findViewById(R.id.frameLayoutFicha)
        frameLayoutFragment = findViewById(R.id.frameLayoutFragment)

        listaFragment = ListaPeliculasFragment()
        listaFragment!!.activityListener = activityListener
        fichaFragment = FichaPeliculaFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (frameLayoutFragment == null) {
            // Están definidos los dos espacios para poner los fragment
            fragmentTransaction.add(R.id.frameLayoutLista, listaFragment!!)
            fragmentTransaction.add(R.id.frameLayoutFicha, fichaFragment!!)
        } else {
            fragmentTransaction.add(R.id.frameLayoutFragment, listaFragment!!)
        }
        fragmentTransaction.commit()
    }

    var activityListener = View.OnClickListener {
        if (frameLayoutFragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutFragment, fichaFragment!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = true
        }
        fichaFragment!!.updateData(listaFragment!!.peliculaSeleccionada)
    }

    override fun onBackPressed() {
        if (segundoFragmentActivo && frameLayoutFragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutFragment, listaFragment!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = false
        } else {
            super.onBackPressed()
        }
    }
}