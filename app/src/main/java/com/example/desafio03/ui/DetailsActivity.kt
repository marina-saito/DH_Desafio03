package com.example.desafio03.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.desafio03.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var img = intent.getStringExtra("img")
        var title = intent.getStringExtra("title")
        var description = intent.getStringExtra("description")
        var date = intent.getStringExtra("date")
        var price = intent.getStringExtra("price")
        var pages = intent.getStringExtra("pages")

        Glide.with(this).asBitmap()
            .load(img)
            .into(ivHero)
        Glide.with(this).asBitmap()
            .load(img)
            .into(ivComic)
        tvTitle.text = title
        tvDescription.text = description
        tvDate.text = date
        tvPrice.text = "$"+price
        tvPages.text = pages


    }
}