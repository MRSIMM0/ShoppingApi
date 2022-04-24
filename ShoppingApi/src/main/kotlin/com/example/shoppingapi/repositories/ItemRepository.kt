package com.example.shoppingapi.repositories

import com.example.shoppingapi.models.Item
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<Item, Int> {
}