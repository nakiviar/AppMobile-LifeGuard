package com.ciber.volley

object Constantes {

  val URL_SERVER ="http://192.168.0.4:8080/rsdami/rs"
 //   val URL_SERVER ="https://b765ec30-ce3b-4699-abe0-ae7dfb6e862f.mock.pstmn.io/rsdami/rs"

    //---------------    PRODUCTOS  -----------------
    val URL_GET_PRODUCTO_LIST =URL_SERVER+"/productos"
    val URL_POST_PRODUCTO_CREATE =URL_SERVER+"/productos"
    val URL_GET_PRODUCTO_BY_ID =URL_SERVER+"/productos/"///ID
    val URL_PUT_PRODUCTO_UPDATE =URL_SERVER+"/productos"
    val URL_GET_PRODUCTO_GET_IMAGEN =URL_SERVER+"/productos/imagenes/"///ID
    val URL_POST_PRODUCTO_ADD_IMAGEN  =URL_SERVER+"/productos/imagenes/Subir/"// ID

    //---------------    CLIENTE  -----------------
    val URL_GET_CLIENTE_LIST = URL_SERVER+"/clientes"
    val URL_POST_CLIENTE_CREATE = URL_SERVER+"/clientes"
    val URL_PUT_CLIENTE_UPDATE = URL_SERVER+"/clientes"
    val URL_GET_CLIENTE_BY_ID = URL_SERVER+"/clientes/"// /ID

    //---------------    BOLETA  -----------------
    val URL_POST_BOLETA_CREATE = URL_SERVER+"/boleta"
    val URL_GET_BOLETA_BY_ID = URL_SERVER+"/boleta/" // /ID = 1
    val URL_GET_BOLETAS_BY_CLIENTE = URL_SERVER+"/boleta/" // /ID = 4
    val URL_PUT_BOLETA_UPDATE = URL_SERVER+"/boleta"
    val URL_POST_BOLETA_ADD_ITEM = URL_SERVER+"/boleta/item"
    val URL_PUT_BOLETA_UPDATE_ITEM = URL_SERVER+"/boleta/item"
    val URL_DELETE_BOLETA_ITEM = URL_SERVER+"/boleta/item/" // /ID

    //---------------    FARMACIA  -----------------
    val URL_GET_FARMACIA_BY_ID = URL_SERVER+"/farmacias/" // /ID = 1
    val URL_POST_FARMACIA_CREATE = URL_SERVER+"/farmacias"
    val URL_PUT_FARMACIA_PUT = URL_SERVER+"/farmacias"
    val URL_GET_FARMACIA_LIST = URL_SERVER+"/farmacias"


}