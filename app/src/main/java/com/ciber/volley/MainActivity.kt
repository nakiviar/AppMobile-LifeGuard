package com.ciber.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.ciber.volley.Models.*
import com.ciber.volley.Utils.MySingleton
import com.ciber.volley.Utils.ProductAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    //ITEMS
    private var listaProductoItems: ArrayList<Item> = ArrayList()
    //CLIENTES

    val adaptador = ProductAdapter(this)
    //  private var request : RequestQueue? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("INFO ","start here")

        //imagenes
        rvProductos.layoutManager = LinearLayoutManager(this)
        rvProductos.adapter = adaptador

//      getProductsRest()
//      createProductRest(producto)
        getProductsImage()
    }
    //FUNCIONES
    //---------------    PRODUCTOS  -----------------
    private fun getProductsRest(){
    val jsonRequest = JsonArrayRequest(
        Request.Method.GET,
        Constantes.URL_GET_PRODUCTO_LIST,
        null,
        Response.Listener     {response ->
            try {
                for (i in 0 until response.length()) {
                    val producto = response.getJSONObject(i)
                    val id = producto.getString("id_producto")
                    val desc = producto.getString("descripcion")
                    val stock = producto.getString("stock")
                    Log.i("res ",id+desc+stock)
                }

            }catch (joex : JSONException){
                println("---------ERROR--EN--EL--JSON-------- $joex")
            }
        } ,
        Response.ErrorListener {
                error -> print(error)
        }
    )
    MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
}
    private fun createProductRest(producto :Producto){
        val requestBody = JSONObject()
        requestBody.put("descripcion", producto.descripcion)
        requestBody.put("stock", producto.cantidad)
        requestBody.put("precio", producto.precio)
        requestBody.put("id_categoria", producto.categoria)
        requestBody.put("unidadMed", producto.unidadMed)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            Constantes.URL_POST_PRODUCTO_CREATE,
            requestBody,
            Response.Listener     {response ->
                try {
                        val desc = response.getString("descripcion")
                        Log.i("INFO ","SE REGISTRO EL PRODUCTO : "+desc)
                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            Response.ErrorListener {
                    error -> print(error)
            }
        )
      MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
    private fun getProductsByIdRest(id : Int){

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            Constantes.URL_GET_PRODUCTO_BY_ID + id,
            null,
            { response ->
                try {
                    val id = response.getString("id_producto")
                    val desc = response.getString("descripcion")
                    val stock = response.getString("stock")
                    Log.i("res ", "Se encontro el producto : " + desc)
                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
           {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
    private fun updateProductRest(producto :Producto){
        val requestBody = JSONObject()

        requestBody.put("id_producto", producto.id)
        requestBody.put("descripcion", producto.descripcion)
        requestBody.put("stock", producto.cantidad)
        requestBody.put("precio", producto.precio)
        requestBody.put("id_categoria", producto.categoria)
        requestBody.put("unidadMed", producto.unidadMed)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            Constantes.URL_PUT_PRODUCTO_UPDATE,
            requestBody,
            Response.Listener     {response ->
                try {
                    for (i in 0 until response.length()) {
                        val desc = response.getString("descripcion")
                        Log.i("INFO ","SE ACTUALIZO EL PRODUCTO : "+desc)
                    }

                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            Response.ErrorListener {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
    fun getProductsImage(){

        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            Constantes.URL_GET_PRODUCTO_LIST,
            null,
            Response.Listener     {response ->
                try {
                    Log.i("RES",response.toString())
                    for (i in 0 until response.length()) {
                        val productoJson = response.getJSONObject(i)
                        val id = productoJson.getInt("id_producto")
                        val desc = productoJson.getString("descripcion")
                        val stock = productoJson.getInt("stock")
                        val precio = productoJson.getDouble("precio")
                        val categoria = productoJson.getInt("id_categoria")
                        val unidadMed = productoJson.getString("unidadMed")
                        val urlImagen = Constantes.URL_GET_PRODUCTO_GET_IMAGEN+id
                        val producto = Item(id,desc,stock,precio,categoria,unidadMed,urlImagen)

                        listaProductoItems.add(producto)
                    }
                    adaptador.agregarElementos(listaProductoItems)
                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            Response.ErrorListener {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
    fun uploadProductImage(){
        // PENDIENTE
        //URL_POST_PRODUCTO_ADD_IMAGEN
    } //AUN EN CONSTRUCCION :V
    //---------------    CLIENTE     -----------------
    private fun getClientsRest():ArrayList<Cliente>{
        var listaClientes: ArrayList<Cliente> = ArrayList()
        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            Constantes.URL_GET_CLIENTE_LIST,
            null,
            Response.Listener     {response ->
                try {
                    for (i in 0 until response.length()) {
                        val clienteJson = response.getJSONObject(i)
                        val id_ciente = clienteJson.getInt("id_ciente")
                        val nombres = clienteJson.getString("nombres")
                        val ape_mat = clienteJson.getString("ape_mat")
                        val ape_pat = clienteJson.getString("ape_pat")
                        val dni = clienteJson.getString("dni")
                        val telefono = clienteJson.getInt("telefono")
                        val cliente = Cliente(id_ciente,nombres,ape_mat,ape_pat,dni,telefono)
                        listaClientes.add(cliente)
                    }

                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            Response.ErrorListener {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
        return listaClientes;
    }
    private fun createClientRest(cliente :Cliente) {
        val requestBody = JSONObject()
        requestBody.put("id_ciente", cliente.id)
        requestBody.put("nombres", cliente.nombres)
        requestBody.put("ape_mat", cliente.ape_mat)
        requestBody.put("ape_pat", cliente.ape_pat)
        requestBody.put("dni", cliente.dni)
        requestBody.put("telefono", cliente.telefono)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            Constantes.URL_POST_CLIENTE_CREATE,
            requestBody,
            Response.Listener
            { response ->
                try {
                    val clienteNuevo = response.getString("id_ciente")
                    Log.i("INFO ", "NUEVO CLIENTE CON EL ID : " + cliente)

                } catch (joex: JSONException) {
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            },
            Response.ErrorListener { error ->
                print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
    private fun getClientByIdRest(id : Int):Cliente{
        var cliente:Cliente=Cliente(0,"","","","",123)
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            Constantes.URL_GET_CLIENTE_BY_ID + id,
            null,
            { response ->

                try {
                        val id_ciente = response.getInt("id_ciente")
                        val nombres = response.getString("nombres")
                        val ape_mat = response.getString("ape_mat")
                        val ape_pat = response.getString("ape_pat")
                        val dni = response.getString("dni")
                        val telefono = response.getInt("telefono")
                    cliente = Cliente(id_ciente,nombres,ape_mat,ape_pat,dni,telefono)
                    Log.i("res ", "Se encontro el cliente nro. : " + id_ciente)


                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")

                }

            } ,
            {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
        return cliente
    }

    //-----------------    BOLETA  --------------------
    private fun getBoletaById(id:Int):Boleta{
      //  URL_GET_BOLETA_BY_ID
        var boleta:Boleta=Boleta()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            Constantes.URL_GET_CLIENTE_BY_ID + id,
            null,
            { response ->
                try {
                    val id_boleta=response.getInt("id_boleta")
                    val id_ciente = response.getInt("id_ciente")
                    val fecha = response.getString("fecha")
                    val id_tienda = response.getInt("id_tienda")
                    val total = response.getDouble("total")

                    boleta = Boleta(id_boleta,id_ciente,fecha,id_tienda,total)
                    Log.i("res ", "Se encontro la boleta nro. : " + id_boleta)

                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
        return boleta
    }
    private fun createBoleta(boleta:Boleta){
        val requestBody = JSONObject()
        requestBody.put("id_cliente", boleta.id_cliente)
        requestBody.put("fecha", boleta.fecha)
        requestBody.put("id_tienda", boleta.id_tienda)
        requestBody.put("total", boleta.total)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            Constantes.URL_POST_BOLETA_CREATE,
            requestBody,
            Response.Listener
            { response ->
                try {
                    val boleta = response.getString("id_boleta")
                    Log.i("INFO ", "Boleta Creada nro. " + boleta)

                } catch (joex: JSONException) {
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            },
            Response.ErrorListener { error ->
                print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }

    //----------------    FARMACIA  -------------------
    private fun getFarmaciaById(id:Int):Farmacia{
        var farmacia:Farmacia=Farmacia()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            Constantes.URL_GET_FARMACIA_BY_ID+ id,
            null,
            { response ->

                try {
                    val id_tienda = response.getInt("id_tienda")
                    val nombre = response.getString("nombre")
                    val ruc = response.getInt("ruc")
                    val telefono = response.getInt("telefono")
                    farmacia = Farmacia(id_tienda,nombre,ruc,telefono)
                    Log.i("res ", "Se encontro la tienda nro. : " + id_tienda)

                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
        return farmacia
    }
    private fun createFarmacia(farmacia: Farmacia){
        val requestBody = JSONObject()
        requestBody.put("nombre", farmacia.nombre)
        requestBody.put("ruc", farmacia.ruc)
        requestBody.put("telefono", farmacia.telefono)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            Constantes.URL_POST_FARMACIA_CREATE,
            requestBody,
            Response.Listener     {response ->
                try {
                        val desc = response.getString("nombre")
                        Log.i("INFO ","SE REGISTRO LA TIENDA - "+desc)

                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            Response.ErrorListener {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
    private fun getListFarmacia():ArrayList<Farmacia>{
        var listaFarmacia: ArrayList<Farmacia> = ArrayList()
        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            Constantes.URL_GET_FARMACIA_LIST,
            null,
            Response.Listener     {response ->
                try {
                    for (i in 0 until response.length()) {
                        val farmaciaJson = response.getJSONObject(i)
                        val id_tienda = farmaciaJson.getInt("id_tienda")
                        val nombre = farmaciaJson.getString("nombre")
                        val ruc = farmaciaJson.getInt("ruc")
                        val telefono = farmaciaJson.getInt("telefono")
                        val farmacia = Farmacia(id_tienda,nombre,ruc, telefono)
                        listaFarmacia.add(farmacia)
                    }

                }catch (joex : JSONException){
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            } ,
            Response.ErrorListener {
                    error -> print(error)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
        return listaFarmacia;
    }

    //FINAL
    }
