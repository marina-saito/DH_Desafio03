package com.example.desafio03.entities

import java.text.SimpleDateFormat


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
        val dates: List<DateMarvel>,
        val prices: List<Price>,
        val images: List<MarvelImage>
){
    fun getDate(): String {
        var dateFmt: String = ""
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        val formatter = SimpleDateFormat("MMMM dd, yyyy")
        this.dates.forEach{
            if (it.type == "onsaleDate") { dateFmt = it.date }
        }
        return formatter.format(parser.parse(dateFmt))
    }
    fun getPrice(): Double {
        var price: Double = 0.0
        prices.forEach{
            if (it.type == "printPrice") { price = it.price }
        }
        return price
    }
    fun getImgUrl(): String {
        return images[0].path+"."+images[0].extension
    }
    override fun toString(): String {
        if (description == null) {
            description = "Sorry, no description available"
        }
        return "id: $id \ntitle: $title \ndescription: $description \npages: $pageCount\ndate: ${this.getDate()} \nprice: ${getPrice()}\nimgURL: ${images[0].path+"."+images[0].extension}"
    }

}

data class DateMarvel(
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

