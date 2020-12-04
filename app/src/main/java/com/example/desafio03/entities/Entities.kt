package com.example.desafio03.entities

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat

class Comic(
    val id: Int,
    val title: String,
    var description: String,
    val pageCount: Int,
    val dates: JsonArray,
    val prices: JsonArray,
    val thumbnail: Map<String,String>
){
    var dateFormatted: String = "Sorry, date is not available."
//    init {
//        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//        val formatter = SimpleDateFormat("MMMM dd, yyyy")
//        val datesArray = Gson().fromJson(this.dates, object : TypeToken<List<Map<String,String>>>(){}.type) as List<Map<String,String>>
//        datesArray.forEach{
//            if (it["type"] == "onsaleDate") { this.dateFormatted = formatter.format(parser.parse(it["date"].toString()))
//            }
//        }
//    }
    fun getDescrip(): String {
        return if (this.description == null) {
            "Sorry, no description available!"
        } else this.description
    }

    fun getDate(): String {
        var dateFmt: String = ""
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        val formatter = SimpleDateFormat("MMMM dd, yyyy")
        val datesArray = Gson().fromJson(dates, object : TypeToken<List<Map<String,String>>>(){}.type) as List<Map<String,String>>
        datesArray.forEach{
            if (it["type"] == "onsaleDate") { dateFmt = it["date"].toString()
            }
        }
        return formatter.format(parser.parse(dateFmt))
    }
    fun getPrice(): Double {
        var price: Double = 0.0
        val pricesArray = Gson().fromJson(prices, object : TypeToken<List<Map<String,String>>>(){}.type) as List<Map<String,String>>
        pricesArray.forEach{
            if (it["type"] == "printPrice") { price = it["price"]?.toDouble() ?: 0.0
            }
        }
        return price
    }
    fun getImgUrl(): String {
        return thumbnail["path"]+"."+thumbnail["extension"]
    }
    override fun toString(): String {
        if (description == null) {
            description = "Sorry, no description available"
        }
        return "id: $id \ntitle: $title \ndescription: ${this.getDescrip()} \npages: $pageCount\ndate: ${this.getDate()} \nprice: ${getPrice()}\nimgURL: ${this.getImgUrl()}"
    }

}


