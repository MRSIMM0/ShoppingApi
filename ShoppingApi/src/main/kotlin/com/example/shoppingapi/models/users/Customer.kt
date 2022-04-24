package com.example.shoppingapi.models.users

import com.example.shoppingapi.models.UserOrder
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name ="Customer")
data class Customer(
    override var username: String,

    override var password: String,

    @OneToMany(mappedBy = "id", cascade = [CascadeType.ALL], orphanRemoval = true)
    var orderList: List<UserOrder?>? = listOf(null)

) : User(null, username,password)