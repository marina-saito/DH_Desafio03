package com.example.desafio03.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.desafio03.R
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        var img = intent.getStringExtra("img")
        Glide.with(this).asBitmap()
            .load(img)
            .into(ivComic)

        ivClose.setOnClickListener {
            finish()
        }
    }
}