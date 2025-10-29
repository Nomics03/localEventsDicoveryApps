package com.adli.eventdiscover

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

class TicketsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        // --- 1. Tab Layout Listener ("Upcoming" / "Past") ---
        // THIS IS THE CORRECTED SECTION
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 1) { // 1 is the "Past" tab
                    // Go to TicketsPastActivity
                    val intent = Intent(this@TicketsActivity, TicketsPastActivity::class.java)
                    // Add flags to prevent animation flicker and stacking history
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Not needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Not needed
            }
        })

        // --- 2. "Let's make plans" Button Listener ---
        val makePlansButton = findViewById<MaterialButton>(R.id.make_plans_button)
        makePlansButton.setOnClickListener {
            // Go to HomepageActivity to find events
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }

        // --- 3. "Find your tickets" Link Listener ---
        val findTicketsLink = findViewById<TextView>(R.id.find_tickets_link)
        findTicketsLink.setOnClickListener {
            // You can decide where this goes, maybe Homepage or a search page
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }


        // --- 4. Bottom Navigation Listener ---
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // This highlights the "Tickets" icon
        bottomNav.selectedItemId = R.id.nav_tickets

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_discover -> {
                    // Go to Homepage
                    val intent = Intent(this, HomepageActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }

                R.id.nav_saved -> {
                    // Go to Saved
                    val intent = Intent(this, SavedActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                }

                R.id.nav_tickets -> {
                    // We are already here, do nothing
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

    override fun onResume() {
        super.onResume()
        // This code ensures the "Upcoming" tab is re-selected
        // when the user presses "Back" from the "Past" activity.
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.getTabAt(0)?.select()
    }
}