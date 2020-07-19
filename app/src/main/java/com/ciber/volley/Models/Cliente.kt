package com.ciber.volley.Models

import java.io.Serializable

class Cliente (
    var id: Int,
    var nombres: String,
    var ape_mat: String,
    var ape_pat: String,
    var dni: String,
    var telefono: Int
    ) : Serializable