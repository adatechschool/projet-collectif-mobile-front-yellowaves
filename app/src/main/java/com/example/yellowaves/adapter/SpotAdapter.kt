package com.example.yellowaves.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yellowaves.MainActivity
import com.example.yellowaves.R
import com.example.yellowaves.SpotModel


class SpotAdapter(
    private val context: MainActivity,
    private val spotList: List<SpotModel>,
    private val layoutId: Int,
) : RecyclerView.Adapter<SpotAdapter.ViewHolder>(){
    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        // image de la plage
        val spotImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_spot, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // récupérer les infos des spots
        val currentSpot = spotList[position]

        //utiliser Glide pour utiliser l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentSpot.image)).into(holder.spotImage)
    }
    override fun getItemCount(): Int = spotList.size


}