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
        val view = inflater?.inflate(R.layout.fragment_first,container,false
        )
        // Création d'une liste qui stock les spots de surfs
        val spotList = arrayListOf<SpotModel>()

        // Ajout de spots dans la liste
        spotList.add(SpotModel(
            adresse = "Pipeline Oahu",
            image = "https://andyoucreations.com/wp-content/uploads/2016/12/Banzai-pipeline.jpg",
            lieu = "Hawaii",
            startSeason = 7,
            endSeason = 8,
            breakType = "Reefbreak",
            difficulty = 4,
            liked = false,
        ))
        spotList.add(SpotModel(
            adresse = "Teahupoo Tahiti",
            image = "https://arquinnicolas.files.wordpress.com/2023/09/teahupoo.jpg",
            lieu = "Tahiti",
            startSeason = 5,
            endSeason = 10,
            breakType = "Reefbreak",
            difficulty = 5,
            liked = false,
        ))
        spotList.add(
            SpotModel(
            adresse = "Mavericks Californie",
            image = "https://ca-times.brightspotcdn.com/dims4/default/07dad63/2147483647/strip/true/crop/6000x4000+0+0/resize/1200x800!/quality/75/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2Fd3%2Fec%2Fb1a6431b4f73b3f7ebc76c5a69fd%2Frsp-0927.jpg",
            lieu = "Californie",
            startSeason = 10,
            endSeason = 3,
            breakType = "Reefbreak",
            difficulty = 5,
            liked = false,
        )
        )
        spotList.add(SpotModel(
            adresse = "Hossegor Landes",
            image = "https://www.guide-des-landes.com/_bibli/annonces/4474/hd/hossegor-7-.jpg",
            lieu = "Landes",
            startSeason = 5,
            endSeason = 10,
            breakType = "Beachbreak",
            difficulty = 3,
            liked = false,
        ))
        spotList.add(SpotModel(
            adresse = "La Gravière Landes",
            image = "https://www.guide-des-landes.com/_bibli/annonces/2742/hd/hossegor-4-.jpg",
            lieu = "Landes",
            startSeason = 5,
            endSeason = 10,
            breakType = "Beachbreak",
            difficulty = 4,
            liked = false,
        ))
        spotList.add(SpotModel(
            adresse = "La Nord Landes",
            image = "https://www.tourismelandes.com/wp-content/uploads/wpetourisme/la-nord-4-2-800x530.jpg",
            lieu = "Landes",
            startSeason = 5,
            endSeason = 10,
            breakType = "Beachbreak",
            difficulty = 3,
            liked = false,
        ))


        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = SpotAdapter(context, spotList, R.layout.item_spot)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}