package com.example.desafio03.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.desafio03.R
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.layout_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)

        view.registerToolbar.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.registerToolbar.navigationIcon?.setTint(resources.getColor(R.color.white))
        view.registerToolbar.navigationIcon?.alpha = 255

        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

}