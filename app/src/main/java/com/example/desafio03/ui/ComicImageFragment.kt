package com.example.desafio03.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.desafio03.R
import kotlinx.android.synthetic.main.fragment_comic_image.*

class ComicImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var img = requireArguments().getString("img")
        Glide.with(this).asBitmap()
            .load(img)
            .into(ivComic)

        ivClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}