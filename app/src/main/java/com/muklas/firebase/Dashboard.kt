package com.muklas.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    val list: ArrayList<Buku> = ArrayList()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        db.collection("buku").addSnapshotListener { querySnapshot, _ ->
            if (querySnapshot != null) {
                list.clear()
                loadData()
            }
        }

        btnAdd.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
        }

    }

    private fun loadData() {
        db.collection("buku")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val buku = Buku(
                        document.id,
                        document.data["judul"].toString(),
                        document.data["deskripsi"].toString(),
                        document.data["penulis"].toString(),
                        document.data["penerbit"].toString(),
                        document.data["tahun"].toString(),
                        document.data["genre"].toString(),
                        document.data["gambar"].toString()
                    )
                    list.add(buku)
                }
                rvBook.layoutManager = LinearLayoutManager(this)
                rvBook.adapter = BookAdapter(list, this)
            }
            .addOnFailureListener { exception ->
                Log.d("DASHBOARD", "Error getting documents: ", exception)
            }
    }

}
