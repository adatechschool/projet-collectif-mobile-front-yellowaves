// MainActivity.kt
package com.example.yellowaves

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.yellowaves.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var splashVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ajout de la logique pour l'écran de démarrage
        if (splashVisible) {
            setContentView(R.layout.launcher)

            Handler(Looper.getMainLooper()).postDelayed({
                // Le reste du code de la MainActivity
                setContentView(R.layout.activity_main)

                //injecter le fragment dans notre boite (fragment_container)
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, FirstFragment(this))
                transaction.addToBackStack(null)
                transaction.commit()
            }, 2000) // 2000 millisecondes (2 secondes)

            splashVisible = false
        }
    }
}
