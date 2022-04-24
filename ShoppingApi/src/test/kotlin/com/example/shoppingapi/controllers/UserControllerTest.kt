package com.example.shoppingapi.controllers

import com.example.shoppingapi.models.dto.CustomerDTO
import com.example.shoppingapi.models.dto.SellerDTO
import com.example.shoppingapi.models.users.Seller
import com.example.shoppingapi.repositories.CustomerRepository
import com.example.shoppingapi.repositories.SellerRepository
import com.example.shoppingapi.utils.customer
import com.example.shoppingapi.utils.entity
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
internal class UserControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var sellerRepository: SellerRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setUp() {

        sellerRepository.deleteAll()
        customerRepository.deleteAll()

        sellerRepository.save(seller())
        sellerRepository.save(seller())
        sellerRepository.save(seller())

        customerRepository.save(customer())
        customerRepository.save(customer())

    }

    private final val uri = "/v1/user"

    @Test
    fun createSeller() {
        val sellerUri = UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "seller")
        webTestClient.post()
            .uri(sellerUri.toUriString())
            .bodyValue(entity())
            .exchange()
            .expectStatus().isCreated
            .expectBody(SellerDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                assertTrue(this!!.id != null)
                assertEquals(this?.username, entity().username)
                assertEquals(this?.password, entity().password)
                assertEquals(this?.itemsId, entity().ordersOrItemsId)
            }
    }

    @Test
    fun createCustomer() {
        val customerUri = UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "customer")
        webTestClient.post()
            .uri(customerUri.toUriString())
            .bodyValue(entity())
            .exchange()
            .expectStatus().isCreated
            .expectBody(CustomerDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                assertTrue(this!!.id != null)
                assertEquals(this?.username, entity().username)
                assertEquals(this?.password, entity().password)
                assertEquals(this?.ordersId, entity().ordersOrItemsId)
            }
    }

    @Test
    fun getAllSellers() {
        val sellerUri = UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "customer")
        webTestClient.get()
            .uri(sellerUri.toUriString())
            .exchange()
            .expectStatus().isOk
            .expectBodyList(SellerDTO::class.java)
            .returnResult()
            .responseBody.run {
                assertEquals(2, this!!.size)
            }
    }

    @Test
    fun getAllCustomers() {
        val customerUri = UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "seller")
        webTestClient.get()
            .uri(customerUri.toUriString())
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CustomerDTO::class.java)
            .returnResult()
            .responseBody.run {
                assertEquals(3, this!!.size)
            }
    }

    @Test
    fun getCustomerById() {
        val customerUri =
            UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "customer").queryParam("id", 1)
        webTestClient.post()
            .uri(customerUri.toUriString())
            .bodyValue(entity())
            .exchange()
            .expectStatus().isCreated
            .expectBodyList(CustomerDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                assertEquals(1, this!!.size)
            }
    }

    @Test
    fun getSellerById() {
        val sellerUri = UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "seller").queryParam("id", 1)
        webTestClient.get()
            .uri(sellerUri.toUriString())
            .exchange()
            .expectStatus().isOk
            .expectBodyList(SellerDTO::class.java)
            .returnResult()
            .responseBody.run {
                assertEquals(1, this!!.size)
            }
    }

    @Test
    fun deleteCustomerById(){
        val sellerUri = UriComponentsBuilder.fromUri(URI.create(uri)).queryParam("type", "customer").queryParam("id", 4)
        webTestClient.delete()
            .uri(sellerUri.toUriString())
            .exchange()
            .expectStatus().isOk
    }
}

