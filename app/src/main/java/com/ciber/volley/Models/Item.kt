package com.ciber.volley.Models

import java.io.Serializable

class Item(
    var id: Int,
    var descripcion: String,
    var cantidad: Int,
    var precio: Double,
    var categoria: Int,
    var unidadMed: String,
    val urlPhoto :String)