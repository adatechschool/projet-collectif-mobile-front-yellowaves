package com.example.yellowaves

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yellowaves.adapter.SpotAdapter

class SpotPopup(
    private val adapter: SpotAdapter,
    private val currentSpot: SpotModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_spot_details)
        setupComponents()
    }

    private fun setupComponents() {
        // actualiser l'image du spot
        val spotImage = findViewById<ImageView>(R.id.imageViewSpot)
        Glide.with(adapter.context).load(Uri.parse(currentSpot.image)).into(spotImage)

        // actualiser le nom du spot
        findViewById<TextView>(R.id.textViewSpotAdresse).text = currentSpot.adresse

        // actualiser le lieu du spot
        findViewById<TextView>(R.id.textViewSpotLieu).text = currentSpot.lieu

        // actualiser le type de spot
        findViewById<TextView>(R.id.textViewSpotBreakType).text = currentSpot.breakType

        // actualiser la difficulté du spot
        findViewById<TextView>(R.id.textViewSpotDifficulty).text = "Difficulté : " + currentSpot.difficulty.toString() + "/5"

        // actualiser la saison du spot
        findViewById<TextView>(R.id.textViewSpotStartSeason).text = "Mois du début de saison : " + currentSpot.startSeason.toString()

        // actualiser la saison du spot
        findViewById<TextView>(R.id.textViewSpotEndSeason).text = "Mois de fin de saison : " + currentSpot.endSeason.toString()

    }
}