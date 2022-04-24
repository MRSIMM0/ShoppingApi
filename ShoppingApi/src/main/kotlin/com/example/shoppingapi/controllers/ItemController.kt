package com.example.shoppingapi.controllers

import com.example.shoppingapi.models.dto.ItemDTO
import com.example.shoppingapi.servces.ItemService
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
@RequestMapping("/v1/item")
class ItemController(val itemService: ItemService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createItem(@RequestBody item: ItemDTO, @RequestParam("id") id: Int): ItemDTO {
        return itemService.createItem(item, id)
    }
    @GetMapping
    fun getItems(@RequestParam("id", required = false, defaultValue = "0") id: Int): List<ItemDTO>{
        return itemService.getItems(id)
    }

    @DeleteMapping
    fun deleteItem(@RequestParam("id") id: Int){
        itemService.deleteItem(id)
    }
}