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

class ComicAdapter(val listener: OnClickComicListener): RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    var listComic = ArrayList<Comic>()

    inner class ComicViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val ivRecycler: ImageView = view.ivRecycler
        val tvRecycler: TextView = view.tvRecycler

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onClickComic(position)
            }
        }
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

    interface OnClickComicListener {
        fun onClickComic(position:Int)
    }
}