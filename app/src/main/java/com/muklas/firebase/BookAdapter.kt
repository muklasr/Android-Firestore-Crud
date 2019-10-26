package com.muklas.firebase

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.muklas.firebase.BookAdapter.ViewHolder
import kotlinx.android.synthetic.main.row_item.view.*

class BookAdapter(val items: ArrayList<Buku>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buku = items.get(position)
        holder.tvTitle?.text = buku.title
        holder.itemView.setOnClickListener {
            val i = Intent(context, UpdateActivity::class.java)
            i.putExtra("id", buku.id)
            i.putExtra("title", buku.title)
            i.putExtra("desc", buku.desc)
            i.putExtra("writer", buku.writer)
            i.putExtra("publisher", buku.publisher)
            i.putExtra("year", buku.year)
            i.putExtra("genre", buku.genre)
            i.putExtra("image", buku.image)
            context.startActivity(i)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tvTitle
    }

}