package com.example.peluchitos_app

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Comunicador {

    var peluchito : MutableList<Peluchito> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        val agregarFragment = AgregarFragment()
        transaction.add(R.id.frameLayout, agregarFragment).commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.nav_agregar -> {
                val agregarFragment = AgregarFragment()
                transaction.replace(R.id.frameLayout, agregarFragment).commit()
            }
            R.id.nav_buscar -> {
                val buscarFragment = BuscarFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("peluches", ArrayList<Peluchito>(peluchito))
                buscarFragment.arguments = bundle
                transaction.replace(R.id.frameLayout, buscarFragment).commit()
            }
            R.id.nav_eliminar -> {
                val eliminarFragment = EliminarFragment()
                transaction.replace(R.id.frameLayout, eliminarFragment).commit()
            }
            R.id.nav_inventario -> {
                val inventarioFragment = InventarioFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("peluches", ArrayList<Peluchito>(peluchito))
                inventarioFragment.arguments = bundle
                transaction.replace(R.id.frameLayout, inventarioFragment).commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun enviarDatos(id: String, nombre: String, cantidad: String, precio: String) {
        var peluche = Peluchito(id,nombre,cantidad,precio)
        peluchito.add(peluche)
    }

    override fun enviarNombreEliminar(nombreEliminar: String) {
        for (i in peluchito) {
            if (i.nombre == nombreEliminar) {
                Toast.makeText(this, "Se ha eliminado el peluche '$nombreEliminar'", Toast.LENGTH_SHORT).show()
                peluchito.remove(i)
                break
            }else{
                Toast.makeText(this, "No hay peluche con ese nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
