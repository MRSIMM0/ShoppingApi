package com.example.shoppingapi.repositories

import com.example.shoppingapi.models.users.Seller
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SellerRepository : CrudRepository<Seller, Int> {
}