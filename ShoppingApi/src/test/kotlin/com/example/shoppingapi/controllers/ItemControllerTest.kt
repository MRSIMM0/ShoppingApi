package com.example.shoppingapi.controllers

import com.example.shoppingapi.models.dto.ItemDTO
import com.example.shoppingapi.repositories.ItemRepository
import com.example.shoppingapi.repositories.SellerRepository
import com.example.shoppingapi.utils.item
import com.example.shoppingapi.utils.itemObj
import com.example.shoppingapi.utils.seller
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
internal class ItemControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var sellerRepository: SellerRepository

    @Autowired
    lateinit var itemRepository: ItemRepository

    private final val uri = "/v1/item"

    @BeforeEach
    fun setUp() {
        sellerRepository.deleteAll()
        itemRepository.deleteAll()

        var seller = sellerRepository.save(seller())

        itemRepository.save(itemObj(sellerId = sellerRepository.findAll().first()))
        itemRepository.save(itemObj(sellerId = sellerRepository.findAll().first()))
        itemRepository.save(itemObj(sellerId = sellerRepository.findAll().first()))

    }

    @Test
    fun createItem() {
        var seller = sellerRepository.findAll().first()
        val url = UriComponentsBuilder.fromUriString(uri).queryParam("id", seller.id).toUriString()
        webTestClient
            .post()
            .uri(url)
            .bodyValue(item())
            .exchange()
            .expectStatus().isCreated
            .expectBody(ItemDTO::class.java)
            .returnResult()
            .responseBody.run {
                assertTrue(this!!.id != null)
                assertEquals(this.name, item().name)
                assertEquals(this.category, item().category)
                assertEquals(this.currency, item().currency)
                assertEquals(this.price, item().price)
                assertEquals(this.sellerId, seller.id)
            }
    }

    @Test
    fun getAllItems() {

        webTestClient
            .get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(ItemDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                assertEquals(3, this!!.size)
            }

    }

    @Test
    fun getOneItem() {
        val url = UriComponentsBuilder.fromUriString(uri).queryParam("id", 2).toUriString()
        webTestClient
            .get()
            .uri(url)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(ItemDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                assertEquals(1, this!!.size)
            }
    }

    @Test
    fun deleteItem() {
        val url = UriComponentsBuilder.fromUriString(uri).queryParam("id", 2).toUriString()
        webTestClient
            .delete()
            .uri(url)
            .exchange()
            .expectStatus().isOk

    }


}