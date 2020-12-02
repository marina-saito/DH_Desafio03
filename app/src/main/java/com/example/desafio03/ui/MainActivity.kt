package com.example.desafio03.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.desafio03.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFrag) // Container dos fragments
        appBarConfiguration = AppBarConfiguration(navController.graph) // Pega o graph do controller


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val navHostFrag = supportFragmentManager.findFragmentById(R.id.navHostFrag)
        val currFrag = navHostFrag?.findNavController()?.currentDestination?.id

        if (currFrag == R.id.registerFragment)
            findNavController(R.id.navHostFrag).popBackStack(R.id.loginFragment, false)

    }

}