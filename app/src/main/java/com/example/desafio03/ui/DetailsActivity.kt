package com.example.desafio03.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.desafio03.R
import kotlinx.android.synthetic.main.activity_details.*
import com.google.android.material.appbar.MaterialToolbar

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(detailsToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        detailsToolbar.setNavigationOnClickListener {
            finish()
        }


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

        ivComic.setOnClickListener {
            val intent = Intent(this@DetailsActivity, FullScreenActivity::class.java)
            intent.putExtra("img", img)
            startActivity(intent)
        }

    }
}