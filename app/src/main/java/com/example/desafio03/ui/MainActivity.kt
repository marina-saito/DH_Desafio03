package com.example.desafio03.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio03.R
import com.example.desafio03.entities.Comic
import com.example.desafio03.service.service
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ComicAdapter.OnClickComicListener {

    lateinit var adapterComic : ComicAdapter
    lateinit var layoutManager : GridLayoutManager

    val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(service) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterComic = ComicAdapter(this)
        layoutManager = GridLayoutManager(this, 3)
        rvComics.adapter = adapterComic
        rvComics.layoutManager = layoutManager
        rvComics.hasFixedSize()

        viewModel.popList(0,15)

        viewModel.listResults.observe(this) {
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
        var date: String = ""
        var price: Double = 0.0
        comic?.dates?.forEach{
            if (it.type == "onsaleDate") { date = it.date }
        }
        comic?.prices?.forEach{
            if (it.type == "printPrice") { price = it.price }
        }
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        if (comic != null) {
            intent.putExtra("img", comic.images[0].path+"."+comic.images[0].extension)
            intent.putExtra("title", comic.title)
            intent.putExtra("description", comic.description)
            intent.putExtra("date", date)
            intent.putExtra("price", price.toString())
            intent.putExtra("pages", comic.pageCount.toString())
        }
        startActivity(intent)
    }
}