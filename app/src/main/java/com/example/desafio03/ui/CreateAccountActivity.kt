package com.example.desafio03.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafio03.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_details.detailsToolbar
import kotlinx.android.synthetic.main.layout_register.*

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        setSupportActionBar(registerToolbar)
        registerToolbar.setNavigationOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}