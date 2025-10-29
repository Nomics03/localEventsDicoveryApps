package com.adli.eventdiscover

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adli.eventdiscover.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    // ADD THIS FUNCTION
    override fun onStart() {
        super.onStart()
        // Check if user is already logged in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is already logged in, go to Homepage
            startActivity(Intent(this, HomepageActivity::class.java))
            finish() // Close LoginActivity so user can't press "back" to it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Handle Login Button Click
        binding.buttonLogin.setOnClickListener {
            // ... (Your existing login code is perfect, no changes needed here)
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomepageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // Handle Sign Up Text Click
        binding.textViewSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}