package com.adli.eventdiscover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TicketsPastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This connects your Kotlin class to your XML layout
        setContentView(R.layout.activity_tickets_past)
    }
}