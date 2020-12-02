package com.example.desafio03.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.desafio03.R
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)

        view.detailsToolbar.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var img = requireArguments().getString("img")
        var title = requireArguments().getString("title")
        var description = requireArguments().getString("description")
        var date = requireArguments().getString("date")
        var price = requireArguments().getString("price")
        var pages = requireArguments().getString("pages")

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
            val bundleRest = Bundle().apply{
                putString("img", img)
            }
            findNavController().navigate(R.id.action_detailsFragment_to_comicImageFragment, bundleRest)
        }

        view.detailsToolbar.title = null
        view.detailsToolbar.navigationIcon?.setTint(resources.getColor(R.color.white))
        view.detailsToolbar.navigationIcon?.alpha = 255
    }

}