package com.example.desafio03.entities

import com.google.gson.JsonObject

data class JsonMarvel(
    val data: Data
)

data class Data(
    val offset: Int,
    val results: ArrayList<Comic>
)

class Comic(
    val id: Int,
    val title: String,
    var description: String,
    val pageCount: Int,
    val dates: List<Date>,
    val prices: List<Price>,
    val images: List<MarvelImage>
){

    override fun toString(): String {
        if (description == null) {
            description = "Sorry, no description available"
        }
        var date: String = ""
        var price: Double = 0.0
        dates.forEach{
            if (it.type == "onsaleDate") { date = it.date }
        }
        prices.forEach{
            if (it.type == "printPrice") { price = it.price }
        }
        return "id: $id \ntitle: $title \ndescription: $description \npages: $pageCount\ndate: ${date} \nprice: ${price}\nimgURL: ${images[0].path+"."+images[0].extension}"
    }

}

data class Date(
    val type: String,
    val date: String
)

data class Price(
    val type: String,
    val price: Double
)

data class MarvelImage(
    val path: String,
    val extension: String
)