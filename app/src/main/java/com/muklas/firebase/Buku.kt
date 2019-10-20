package com.muklas.firebase

class Buku (val title: String,val desc: String, val writer: String, val publisher: String,val year: String,val genre: String,val image: String){
    fun toHashMap(): HashMap<String, String> = hashMapOf(
        "judul" to this.title,
        "deskripsi" to this.desc,
        "penulis" to this.writer,
        "penerbit" to this.publisher,
        "tahun" to this.year,
        "genre" to this.genre,
        "gambar" to this.image
    )
}