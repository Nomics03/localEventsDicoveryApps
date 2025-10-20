package com.adli.eventdiscover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Make it extend AppCompatActivity to turn it into an Activity
class SavedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line connects your new Kotlin class to your XML layout
        setContentView(R.layout.activity_save)
    }
}