package com.example.shoppingapi.repositories

import com.example.shoppingapi.models.UserOrder

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<UserOrder,Int>{

}
