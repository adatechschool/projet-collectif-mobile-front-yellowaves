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

        GlobalScope.launch(Dispatchers.IO) {
            val airtableUrl = "https://api.airtable.com/v0/appVl0FZkV27tV76j/Surf%20Destinations"
            val apiKey =
                "patIgAT65ktiqRvLP.f4904c066d70a410629be084aa2498bef5ee8c739d7834af73f3ff3a58e72f9c"
            try {
                val jsonString = URL(airtableUrl)
                    .openConnection()
                    .apply {
                        setRequestProperty("Authorization", "Bearer $apiKey")
                        setRequestProperty("Content-Type", "application/json")
                    }
                    .getInputStream()
                    .bufferedReader()
                    .use { it.readText() }

                val jsonArray = JSONObject(jsonString).getJSONArray("records")

                for (i in 0 until jsonArray.length()) {
                    val record = jsonArray.getJSONObject(i).getJSONObject("fields")
                    Log.i("VotreTag1", "Contenu JSON : ${record}")
                    Log.i("VotreTag2", "Contenu JSON : ${record.getString("Destination")}")
                    //Log.i("VotreTag3", "Contenu JSON : ${record.getString("Image")}")
                    Log.i("VotreTag3", "Contenu JSON : ${record.getString("Address")}")
                    //Log.i("VotreTag4", "Contenu JSON : "https:/\/www.tourismelandes.com/wp-content/uploads/wpetourisme/la-nord-4-2-800x530.jpg"),
                    Log.i(
                        "VotreTag5",
                        "Contenu JSON : ${record.getString("Peak Surf Season Begins")}"
                    )
                    Log.i(
                        "VotreTag6",
                        "Contenu JSON : ${record.getString("Peak Surf Season Ends")}"
                    )
                    Log.i("VotreTag7", "Contenu JSON : ${record.getString("Surf Break")}")
                    Log.i("VotreTag8", "Contenu JSON : ${record.getString("Difficulty Level")}")

                    spotList.add(
                        SpotModel(
                            adresse = record.getString("Destination"),
                            image = "https://ca-times.brightspotcdn.com/dims4/default/07dad63/2147483647/strip/true/crop/6000x4000+0+0/resize/1200x800!/quality/75/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2Fd3%2Fec%2Fb1a6431b4f73b3f7ebc76c5a69fd%2Frsp-0927.jpg",
                            lieu = record.getString("Address"),
                            startSeason = record.getString("Peak Surf Season Begins"),
                            endSeason = record.getString("Peak Surf Season Ends"),
                            breakType = record.getString("Surf Break"),
                            difficulty = record.getInt("Difficulty Level")
                        )
                    )
                    Log.i("VotreTag9", "Contenu JSON : ${spotList}")
                    withContext(Dispatchers.Main) {
                        val verticalRecyclerView =
                            view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
                        verticalRecyclerView?.adapter = SpotAdapter(context, spotList, R.layout.item_spot)
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Mettre à jour l'UI après la récupération des données


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