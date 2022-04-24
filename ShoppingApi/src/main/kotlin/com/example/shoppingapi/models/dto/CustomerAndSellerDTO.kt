package com.example.shoppingapi.models.dto

class CustomerAndSellerDTO (
    val id: Int?,

    var username: String,

    var password: String,

    var ordersOrItemsId: List<Int?>? = listOf(null)
    )
