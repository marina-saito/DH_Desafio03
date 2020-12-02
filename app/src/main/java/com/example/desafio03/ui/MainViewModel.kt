package com.example.desafio03.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio03.entities.Comic
import com.example.desafio03.service.Service
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(val service: Service): ViewModel() {

    var listResults = MutableLiveData<List<Comic>>()

    fun popList(offset: Int, limit: Int) {
        val startTime =   System.nanoTime()
        //Log.i("Inicio popList", " +  ${((System.nanoTime()-startTime)/1000000)}")
        viewModelScope.launch{
            //Log.i("Inicio Scope", " +  ${((System.nanoTime()-startTime)/1000000)}")
            val jsonMarvel = service.getComics(
                "issueNumber",
                offset,
                limit,
                2901,
                "bd3824e8d6525bd0cd6f7e533dd7b322",
                "14f117b5a14c58b760752ad202e7e617"
            )
            //Log.i("Fim requisicao", " +  ${((System.nanoTime()-startTime)/1000000)}")
            listResults.value = jsonMarvel.data.results
            //Log.i("Atualizado mutable", " +  ${((System.nanoTime()-startTime)/1000000)}")
            //Log.i("XXXXX", jsonMarvel.data.results[0].toString())
        }
    }

}