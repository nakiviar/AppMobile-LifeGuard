package com.ciber.volley.Models

import java.io.Serializable

class Producto(
    var id: Int,
    var descripcion: String,
    var cantidad: Int,
    var precio: Double,
    var categoria: Int,
    var unidadMed: String
) : Serializable
