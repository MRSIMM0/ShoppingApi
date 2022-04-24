package com.example.shoppingapi.models

import com.example.shoppingapi.models.users.Customer
import javax.persistence.*

@Entity
data class UserOrder(
    @Id
    @GeneratedValue
    val id: Int?,

    val date: String,

    val orderStatus: String,

    @OneToMany(fetch = FetchType.LAZY)
    val items: List<Item?>? = listOf(null),

    @ManyToOne(fetch = FetchType.LAZY)
    val user: Customer

)