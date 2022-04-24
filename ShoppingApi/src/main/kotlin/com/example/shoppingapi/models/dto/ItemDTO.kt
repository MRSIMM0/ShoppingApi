package com.example.shoppingapi.models.dto

import com.example.shoppingapi.models.CurrencyEnum
import com.example.shoppingapi.models.ItemCategory


class ItemDTO(
    val id: Int?,

    var name: String,

    var category: String = ItemCategory.MISC.name,

    var price: Int,

    var currency: String = CurrencyEnum.PLN.name,

    val sellerId: Int?
)
