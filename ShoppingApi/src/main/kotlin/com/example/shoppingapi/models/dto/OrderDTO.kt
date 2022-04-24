package com.example.shoppingapi.models.dto

class OrderDTO(
    val id: Int?,

    val date: String,

    val orderStatus: String,

    val itemsId: List<Int?>? = listOf(null),

    val userId: Int?

)