package com.example.peluchitos_app

interface Comunicador {
    fun enviarDatos(id: String, nombre: String, cantidad: String, precio: String)
    fun enviarNombreEliminar(nombreEliminar: String)
}