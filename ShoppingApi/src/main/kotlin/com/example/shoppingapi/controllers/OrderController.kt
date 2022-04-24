package com.example.shoppingapi.controllers

import com.example.shoppingapi.models.dto.OrderDTO
import com.example.shoppingapi.servces.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/order")
class OrderController(
    val orderService: OrderService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody orderDTO: OrderDTO,@RequestParam("id") id:Int):OrderDTO?{
       return orderService.createOrder(orderDTO,id)
    }
    @GetMapping
    fun getOrder(@RequestParam("id", required = false,defaultValue = "0") id:Int):List<OrderDTO>{
        return orderService.getOrder(id)
    }
    @DeleteMapping
    fun deleteOrder(@RequestParam("id")id:Int){
        return orderService.deleteOrder(id)
    }
}