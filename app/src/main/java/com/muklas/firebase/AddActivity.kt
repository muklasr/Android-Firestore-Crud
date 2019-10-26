package com.muklas.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnBack.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()
            val writer = etWriter.text.toString()
            val publisher = etPublisher.text.toString()
            val year = etYear.text.toString()
            val genre = etGenre.text.toString()

            val buku = Buku("", title, desc, writer, publisher, year, genre, "")

            val db = FirebaseFirestore.getInstance()

            db.collection("buku")
                .add(buku.toHashMap())
                .addOnSuccessListener {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show()
                }
        }
    }
}