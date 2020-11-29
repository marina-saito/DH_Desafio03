package com.example.desafio03.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafio03.R
import com.example.desafio03.entities.Comic
import kotlinx.android.synthetic.main.item_recycler.view.*

class ComicAdapter(): RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    var listComic = ArrayList<Comic>()

    class ComicViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivRecycler: ImageView = view.ivRecycler
        val tvRecycler: TextView = view.tvRecycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        var comic = listComic[position]
        Glide.with(holder.itemView).asBitmap()
            .load(comic.images[0].path+"."+comic.images[0].extension)
            .into(holder.ivRecycler)
        holder.tvRecycler.text = "#${comic.id}"
    }

    override fun getItemCount() = listComic.size

    fun addList(list: List<Comic>) {
        listComic.addAll(list)
        notifyDataSetChanged()
    }
}