package com.example.shoppingapi.utils

import com.example.shoppingapi.models.CurrencyEnum
import com.example.shoppingapi.models.Item
import com.example.shoppingapi.models.ItemCategory
import com.example.shoppingapi.models.UserOrder
import com.example.shoppingapi.models.dto.CustomerAndSellerDTO
import com.example.shoppingapi.models.dto.ItemDTO
import com.example.shoppingapi.models.dto.OrderDTO
import com.example.shoppingapi.models.users.Customer
import com.example.shoppingapi.models.users.Seller

fun item(id: Int? = null, name: String = "TEST",sellerId: Int? = null) =
    ItemDTO(id, name, ItemCategory.FOOD.name, 2, CurrencyEnum.EURO.name, sellerId)

fun itemObj(id: Int? = null, name: String = "TEST",sellerId: Seller? = null) = Item(id, name, ItemCategory.FOOD, 2, CurrencyEnum.EURO, sellerId)

fun entity(id: Int? = null) = CustomerAndSellerDTO(id, "Test", "Test", listOf())

fun customer(id: Int? = null) = Customer("Test", "Test", listOf())

fun seller(id: Int? = null) = Seller("Test", "Test", listOf())

fun order(id: Int? = null,user: Customer) = UserOrder(id,"Test","Test", listOf(null),user)

fun orderDTO(id: Int? = null,user: Customer? = null) = OrderDTO(id,"Test","Test", listOf(null),null)