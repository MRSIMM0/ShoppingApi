package com.example.shoppingapi.models.dto

class CustomerDTO(

    val id: Int?,

    var username: String,

    var password: String,

    var ordersId: List<Int?>? = listOf(null)
        )
