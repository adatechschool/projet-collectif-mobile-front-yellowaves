package com.example.yellowaves.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yellowaves.MainActivity
import com.example.yellowaves.R
import com.example.yellowaves.SpotModel
import com.example.yellowaves.SpotPopup


class SpotAdapter(
    val context: MainActivity,
    private val spotList: List<SpotModel>,
    private val layoutId: Int,
) : RecyclerView.Adapter<SpotAdapter.ViewHolder>() {
    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // image de la plage
        val spotImage = view.findViewById<ImageView>(R.id.image_item)
        val spotName = view.findViewById<TextView>(R.id.lieu_item)
        val spotLocation = view.findViewById<TextView>(R.id.adresse_item)
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

        //mettre à jour le nom du lieu
        holder.spotName.text = currentSpot.lieu

        //mettre à jour l'adresse
        holder.spotLocation.text = currentSpot.adresse

        // interaction lors du clic sur un spot
        holder.itemView.setOnClickListener {
            //afficher le popup
            SpotPopup(this, currentSpot).show()
        }
    }

    override fun getItemCount(): Int = spotList.size


}