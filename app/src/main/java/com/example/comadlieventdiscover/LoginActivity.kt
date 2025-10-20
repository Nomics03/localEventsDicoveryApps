package com.adli.eventdiscover

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adli.eventdiscover.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // Using ViewBinding for easy access to UI elements
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // When the Login button is clicked
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sign in with Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login successful, check if user is admin or regular user
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                        val user = auth.currentUser
                        if (user != null && user.email == "admin@eventdiscover.com") {
                            // User is an admin, go to Admin Dashboard
                            startActivity(Intent(this, SavedActivity::class.java))
                        } /*else {
                            // User is a regular user, go to Home screen
                            startActivity(Intent(this, EventDetailsActivity::class.java))
                        }*/
                        finish() // Close the login activity so the user can't go back to it

                    } else {
                        // Login failed, show an error message
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // When the "Sign Up" text is clicked
        binding.textViewSignUp.setOnClickListener {
            // Go to SignUpActivity
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
