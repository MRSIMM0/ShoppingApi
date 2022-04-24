package com.example.shoppingapi.models.users

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class User(
    @Id
    @GeneratedValue
    open val id: Int?,

    open var username: String,

    open var password: String

    )