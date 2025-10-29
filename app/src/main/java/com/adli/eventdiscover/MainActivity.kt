package com.adli.eventdiscover

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Handler to wait a few seconds before checking login status
        Handler(Looper.getMainLooper()).postDelayed({
            // Check if user is already logged in
            if (auth.currentUser != null) {
                // --- THIS IS THE FIX ---
                // If yes, go to HomepageActivity
                startActivity(Intent(this, HomepageActivity::class.java))
            } else {
                // If no, go to LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish() // Close MainActivity so the user can't go back
        }, 2000) // Wait 2 seconds
    }
}