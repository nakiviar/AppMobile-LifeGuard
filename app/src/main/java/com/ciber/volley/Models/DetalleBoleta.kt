package com.ciber.volley.Models

import java.io.Serializable

class DetalleBoleta  (
    var id_detalle: Int?=0,
    var id_boleta: Int?=0,
    var id_producto: Int?=0,
    var precio: Double?=0.0,
    var cantidad: Int?=0
) : Serializable

