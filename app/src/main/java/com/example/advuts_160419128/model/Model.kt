package com.example.advuts_160419128.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    val title:String?,
    val author:String?,
    val category:String?,
    val description:String?,
    val photourl:String?
)

data class Author(
    val name: String?,
    val photourl: String?
)

