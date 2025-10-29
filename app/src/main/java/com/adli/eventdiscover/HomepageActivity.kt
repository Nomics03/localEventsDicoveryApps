package com.adli.eventdiscover

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

// Make sure your Activity file matches your XML file name
class HomepageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage) // Use your XML file name here

        // --- 1. Search Bar Listener ---
        val searchBar = findViewById<MaterialCardView>(R.id.search_bar_card)
        searchBar.setOnClickListener {
            // TODO: You can change this to start a new SearchActivity
            Toast.makeText(this, "Search clicked!", Toast.LENGTH_SHORT).show()
        }

        // --- 2. Filter Button Listener ---
        val filterButton = findViewById<ImageView>(R.id.filter_button)
        filterButton.setOnClickListener {
            // TODO: You can change this to show a filter dialog
            Toast.makeText(this, "Filter clicked!", Toast.LENGTH_SHORT).show()
        }

        // --- 3. Bottom Navigation Listener ---
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // This highlights the 'Discover' icon by default
        bottomNav.selectedItemId = R.id.nav_discover

        // Set the listener for when an item is clicked
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_discover -> {
                    // We are already here, do nothing
                    true // Return true to show item as selected
                }

                R.id.nav_saved -> {
                    // Go to SavedActivity
                    val intent = Intent(this, SavedActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // Optional: for smooth switch
                    startActivity(intent)
                    true // Return true to show item as selected
                }

                R.id.nav_tickets -> {
                    // Go to TicketsActivity
                    val intent = Intent(this, TicketsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // Optional: for smooth switch
                    startActivity(intent)
                    true // Return true to show item as selected
                }

                R.id.nav_account -> {
                    // TODO: Create an AccountActivity.kt file
                    // val intent = Intent(this, AccountActivity::class.java)
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    // startActivity(intent)
                    Toast.makeText(this, "Account clicked", Toast.LENGTH_SHORT).show()
                    true // Return true to show item as selected
                }

                else -> false // Default case
            }
        }
    }

    // Example function to load a Fragment (you would need to create these)
    /*
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.recycler_view_events, fragment) // Note: Replace the content area
            .commit()
    }
    */
}