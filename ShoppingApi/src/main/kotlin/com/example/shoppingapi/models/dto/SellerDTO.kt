package com.example.shoppingapi.models.dto

import com.example.shoppingapi.models.Item
import javax.persistence.CascadeType
import javax.persistence.OneToMany

class SellerDTO
    (

    val id: Int?,

    var username: String,

    var password: String,

    var itemsId: List<Int?>? = listOf(null)
)
