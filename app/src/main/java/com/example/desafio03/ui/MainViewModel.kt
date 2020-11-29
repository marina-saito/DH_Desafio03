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
        viewModelScope.launch{
            val jsonMarvel = service.getComics(
                "issueNumber",
                offset,
                limit,
                1,
                "6eb7e8896ec5850c52515a8a23ee97f0",
                "40a3aa568bb269dfad85ae0c4a297181"
            )
            listResults.value = jsonMarvel.data.results
            //val results = jsonMarvel.get("data").asJsonObject.get("results").asJsonObject
            //val comics = Gson().fromJson(results, object: TypeToken<List<Comic>>(){}.type) as List<Comic>
            Log.i("XXXXX", jsonMarvel.data.results[0].toString())
        }
    }

}