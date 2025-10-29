package com.adli.eventdiscover

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adli.eventdiscover.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference // Firebase Database reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Sign Up Button Click Listener
        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()
            val name = binding.editTextName.text.toString().trim() // New field for the user's name

            // Validate inputs
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create user with Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Successfully created the user
                        val user = auth.currentUser

                        // Create a user object to save to Realtime Database
                        val userData = HashMap<String, Any>()
                        userData["name"] = name
                        userData["email"] = email
                        userData["uid"] = user?.uid ?: "" // Using user UID as the key

                        // Save user data to Firebase Realtime Database
                        if (user != null) {
                            database.child("users").child(user.uid).setValue(userData)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        // Registration successful and data is saved to Realtime Database
                                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this, LoginActivity::class.java)) // Navigate to LoginActivity
                                        finish() // Close the SignUpActivity
                                    } else {
                                        // Failed to save user data to Firebase Realtime Database
                                        Toast.makeText(this, "Failed to save user data: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                        }
                    } else {
                        // Registration failed
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // When "Log In" text is clicked, go to LoginActivity
        binding.textViewLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
