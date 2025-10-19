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

        // Handler untuk menunggu beberapa saat sebelum memeriksa status log masuk
        Handler(Looper.getMainLooper()).postDelayed({
            // Periksa jika pengguna sudah log masuk
            if (auth.currentUser != null) {
                // Jika ya, terus ke HomeActivity (akan kita cipta nanti)
                // Untuk sekarang, kita akan hantar ke LoginActivity sebagai placeholder
                // startActivity(Intent(this, HomeActivity::class.java))
                startActivity(Intent(this, LoginActivity::class.java)) // Placeholder
            } else {
                // Jika tidak, pergi ke LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish() // Tutup MainActivity supaya pengguna tidak boleh kembali ke sini
        }, 2000) // Tunggu 2 saat (2000 milisaat)
    }
}