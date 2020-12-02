package com.example.desafio03.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio03.R
import com.example.desafio03.service.service
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ComicAdapter.OnClickComicListener {

    lateinit var adapterComic : ComicAdapter
    lateinit var layoutManager : GridLayoutManager

    val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(service) as T
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterComic = ComicAdapter(this)
        layoutManager = GridLayoutManager(context, 3)
        rvComics.adapter = adapterComic
        rvComics.layoutManager = layoutManager
        rvComics.hasFixedSize()

        viewModel.popList(0,15)

        viewModel.listResults.observe(viewLifecycleOwner) {
            adapterComic.addList(it)
        }

        //setScroller()
    }

    // TODO: arrumar, está doidona!
    fun setScroller() {
        rvComics.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // dy é tipo delta y, sabe se rolou a tela
                // Portanto o if é verdadeiro quando acontece a rolagem
                if (dy > 0) {
                    val litem = layoutManager.itemCount // conta quantos itens estão visiveis na tela
                    val vitem = layoutManager.findFirstCompletelyVisibleItemPosition() // pega primeiro item visivel na tela
                    val itens = adapterComic.itemCount
                    // Se os itens que já passaram + os itens visiveis = total de itens, fazer nova requisição dos proximos itens
                    if (litem + vitem >= itens){
                        viewModel.popList(itens, 12)
                    }
                }
            }
        })
    }

    override fun onClickComic(position: Int) {
        var comic = viewModel.listResults.value?.get(position)
        //f (comic != null) {
            val dateOnSale = comic!!.getDate()
            val price = comic!!.getPrice()
            val imgURL = comic.getImgUrl()
            val bundleRest = Bundle().apply {
                putString("img", imgURL)
                putString("title", comic.title)
                putString("description", comic.description)
                putString("date", dateOnSale)
                putString("price", price.toString())
                putString("pages", comic.pageCount.toString())
            }
        //}
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundleRest)
    }

}