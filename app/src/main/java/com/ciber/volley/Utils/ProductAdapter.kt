package com.ciber.volley.Utils

import android.app.Activity
import android.graphics.BitmapFactory
import android.support.v4.os.IResultReceiver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciber.volley.Models.Item
import com.ciber.volley.Models.Producto
import com.ciber.volley.R
import java.net.URL
import kotlin.concurrent.thread

class ProductAdapter  (val actividad: Activity) : RecyclerView.Adapter<ProductAdapter.ProdViewHolder>(){

    var  listaItems= ArrayList<Item>()

    class ProdViewHolder (view: View) :RecyclerView.ViewHolder(view){
        val uiImagen= view.findViewById<ImageView>(R.id.imgFotoProd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdViewHolder {
        return ProdViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_producto, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listaItems.size
    }

    override fun onBindViewHolder(holder: ProdViewHolder, position: Int) {
        val unItem = listaItems[position]
        cargarImagen(holder.uiImagen,unItem.urlPhoto,actividad)

    }

    fun agregarElementos(nuevaLista : ArrayList<Item>){
        listaItems.addAll(nuevaLista)
        notifyDataSetChanged()
    }
    fun cargarImagen(uiImagen : ImageView, url: String,actividad:Activity) {
        thread {
            val urlObj = URL(url)
            val bmap = BitmapFactory.decodeStream(urlObj.openConnection().getInputStream())
            actividad.runOnUiThread {
                uiImagen.setImageBitmap(bmap)
            }
        }
    }

}