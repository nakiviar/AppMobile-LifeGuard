package com.ciber.volley.Models

import java.io.Serializable

class Boleta  (
    var id_boleta: Int?=0,
    var id_cliente: Int?=0,
    var fecha: String?="06-06-06",
    var id_tienda: Int?=0,
    var total: Double?=0.0
) : Serializable