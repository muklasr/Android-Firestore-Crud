package com.muklas.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        etTitle.setText(intent.getStringExtra("title"))
        etDesc.setText(intent.getStringExtra("desc"))
        etWriter.setText(intent.getStringExtra("writer"))
        etPublisher.setText(intent.getStringExtra("publisher"))
        etYear.setText(intent.getStringExtra("year"))
        etGenre.setText(intent.getStringExtra("genre"))

        btnBack.setOnClickListener{
            finish()
        }

        btnSave.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            db.collection("buku").document(intent.getStringExtra("id"))
                .update(
                    mapOf(
                        "judul" to etTitle.text.toString(),
                        "deskripsi" to etDesc.text.toString(),
                        "penulis" to etWriter.text.toString(),
                        "penerbit" to etPublisher.text.toString(),
                        "tahun" to etYear.text.toString(),
                        "genre" to etGenre.text.toString()
                    )
                )
                .addOnSuccessListener {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show()
                }
        }
    }
}
