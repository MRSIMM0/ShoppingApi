package com.example.shoppingapi.repositories

import com.example.shoppingapi.models.users.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<Customer,Int>