package com.adli.eventdiscover

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class SavedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        // --- 1. Find Fun Button Listener ---
        // THIS IS THE CORRECTED SECTION
        val findFunButton = findViewById<MaterialButton>(R.id.find_fun_button)

        // Set a click listener
        findFunButton.setOnClickListener {
            // Create an Intent to go to the HomepageActivity
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }

        // --- 2. Bottom Navigation Listener ---

        // Find the navigation bar by its ID
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // This line makes sure the "Saved" icon is highlighted when you are on this page.
        bottomNav.selectedItemId = R.id.nav_saved

        // Set the listener for when an item is clicked
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_discover -> {
                    // Go to Homepage
                    val intent = Intent(this, HomepageActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // Optional: for smooth switch
                    startActivity(intent)
                    true
                }

                R.id.nav_saved -> {
                    // We are already on this page, do nothing
                    true
                }

                R.id.nav_tickets -> {
                    // Go to Tickets
                    val intent = Intent(this, TicketsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // Optional: for smooth switch
                    startActivity(intent)
                    true
                }

                R.id.nav_account -> {
                    // TODO: Create an AccountActivity.kt and go to it
                    // val intent = Intent(this, AccountActivity::class.java)
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    // startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}