package com.example.peluchitos_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_buscar.*

class InventarioFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventario, container, false)

        var peluche = arguments?.getParcelableArrayList<Peluchito>("peluches")!!
        
        recyclerView  = view.findViewById(R.id.recycler) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)

        val pelucheAdapter = PeluchitoAdapter(peluche,view.context)
        recyclerView.adapter = pelucheAdapter

        return view
    }
}