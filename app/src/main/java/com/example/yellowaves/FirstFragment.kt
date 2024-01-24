package com.example.yellowaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.example.yellowaves.adapter.SpotAdapter
import com.example.yellowaves.databinding.FragmentFirstBinding
//import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment(
    private val context: MainActivity
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(
            R.layout.fragment_first, container, false
        )
//        // Création d'une liste qui stock les spots de surfs
        val spotList = arrayListOf<SpotModel>()
        // Récupération des données depuis Airtableç
//        val client = OkHttpClient()
//
//        val request = Request.Builder()

        // ...

        GlobalScope.launch(Dispatchers.IO) {
            val apiUrl = "http://localhost:8080/spots"

            try {
                val jsonString = URL(apiUrl)
                    .openConnection()
                    .getInputStream()
                    .bufferedReader()
                    .use { it.readText() }

                val jsonArray = JSONArray(jsonString)

                for (i in 0 until jsonArray.length()) {
                    val spotJson = jsonArray.getJSONObject(i)

                    val destination = spotJson.getString("destination")
                    val difficulty = spotJson.getInt("difficulty")
                    val destinationStateCountry = spotJson.getString("destinationStateCountry")
                    val photos = spotJson.getString("photos")
                    val surfBreak = spotJson.getString("surfBreak")
                    val peakSurfSeasonBegins = spotJson.getString("peakSurfSeasonBegins")
                    val peakSurfSeasonEnds = spotJson.getString("peakSurfSeasonEnds")

                    // Création de l'objet SpotModel
                    val spotModel = SpotModel(
                        adresse = destination,
                        image = photos,
                        lieu = destinationStateCountry,
                        startSeason = peakSurfSeasonBegins,
                        endSeason = peakSurfSeasonEnds,
                        breakType = surfBreak,
                        difficulty = difficulty
                    )

                    spotList.add(spotModel)
                }

                withContext(Dispatchers.Main) {
                    val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
                    verticalRecyclerView?.layoutManager = LinearLayoutManager(context)
                    val spotAdapter = SpotAdapter(context, spotList, R.layout.item_spot)
                    verticalRecyclerView?.adapter = spotAdapter
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return view
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}