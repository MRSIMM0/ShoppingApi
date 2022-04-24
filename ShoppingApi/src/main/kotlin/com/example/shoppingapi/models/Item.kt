package com.example.shoppingapi.models

import com.example.shoppingapi.models.users.Seller
import javax.persistence.*

@Entity
class Item(
    @Id
    @GeneratedValue
    val id: Int?,

    var name: String,

    var category: ItemCategory,

    var price: Int,

    var currency: CurrencyEnum,

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="seller_id", nullable = false)
    val seller: Seller?
)