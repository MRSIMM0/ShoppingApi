package com.example.shoppingapi.models.users

import com.example.shoppingapi.models.Item
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "Seller")
data class Seller(
    override var username: String,

    override var password: String,

    @OneToMany(
        mappedBy = "seller", cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var items: List<Item?>? = listOf(null)

) : User(null, username, password)