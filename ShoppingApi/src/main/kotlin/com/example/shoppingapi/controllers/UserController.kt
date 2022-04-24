package com.example.shoppingapi.controllers

import com.example.shoppingapi.models.dto.CustomerAndSellerDTO
import com.example.shoppingapi.servces.handlers.HandleUser

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController(
    val handleUser: HandleUser,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @RequestBody customerOrSellerDTO: CustomerAndSellerDTO?,
        @RequestParam(name = "type") type: String
    ): Any? {
        return handleUser.customerSellerFactory(type, customerOrSellerDTO!!)
    }

    @GetMapping
    fun getUser(
        @RequestParam(name = "type") type: String,
        @RequestParam(name = "id", required = false, defaultValue = "0") id: Int
    ): Any? {
        return handleUser.getAllByType(type, id)
    }

    @DeleteMapping
    fun deleteUser(
        @RequestParam(name = "type") type: String,
        @RequestParam(name = "id") id: Int
    ) {
        handleUser.delete(type, id)
    }


}