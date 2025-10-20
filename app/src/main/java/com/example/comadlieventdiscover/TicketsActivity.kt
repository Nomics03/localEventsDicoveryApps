package com.adli.eventdiscover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TicketsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line connects the Kotlin code to your XML design
        setContentView(R.layout.activity_tickets)
    }
}