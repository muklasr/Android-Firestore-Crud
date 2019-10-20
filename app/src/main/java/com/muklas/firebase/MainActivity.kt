package com.muklas.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(FirebaseAuth.getInstance().currentUser != null) {
            val i = Intent(this, Dashboard::class.java)
            startActivity(i)
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please insert email and password!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        return@addOnCompleteListener
                    } else {
                        Toast.makeText(this, "Successfully Login", Toast.LENGTH_LONG).show()
                        val i = Intent(this, Dashboard::class.java)
                        startActivity(i)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                }
        }

        btnSignup.setOnClickListener {
            val i = Intent(this, Signup::class.java)
            startActivity(i)
        }
    }
}
