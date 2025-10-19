package com.adli.eventdiscover

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adli.eventdiscover.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Apabila butang Daftar (Sign Up) ditekan
        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            // Validasi input
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Sila isi semua ruangan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Kata laluan tidak sepadan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cipta pengguna baru dengan Firebase
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Pendaftaran berjaya, pergi ke skrin utama (HomeActivity)
                        Toast.makeText(this, "Pendaftaran berjaya!", Toast.LENGTH_SHORT).show()
                        // startActivity(Intent(this, HomeActivity::class.java))
                        // finish()
                    } else {
                        // Pendaftaran gagal, tunjukkan mesej ralat
                        Toast.makeText(this, "Pendaftaran gagal: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // Apabila teks "Log In" ditekan
        binding.textViewLogin.setOnClickListener {
            // Kembali ke LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}