package com.example.shoppingapi.controllers

import com.example.shoppingapi.models.dto.OrderDTO
import com.example.shoppingapi.repositories.CustomerRepository
import com.example.shoppingapi.repositories.OrderRepository
import com.example.shoppingapi.utils.customer
import com.example.shoppingapi.utils.order
import com.example.shoppingapi.utils.orderDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrderControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository

    private var uri = "/v1/order"

    @BeforeEach
    internal fun setUp() {
        customerRepository.deleteAll()
        customerRepository.save(customer())
        orderRepository.save(order(user = customerRepository.findAll().first()))
    }

    @Test
    fun createOrder() {

        var url = UriComponentsBuilder.fromUriString(uri).queryParam("id", 1).toUriString()

        webTestClient.post().uri(url)
            .bodyValue(orderDTO())
            .exchange()
            .expectStatus().isCreated
            .expectBody(OrderDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                Assertions.assertTrue(this!!.id != null)
            }
    }

    @Test
    fun getOrder() {

        var url = UriComponentsBuilder.fromUriString(uri).queryParam("id", 2).toUriString()

        webTestClient.get().uri(url)

            .exchange()
            .expectStatus().isOk
            .expectBodyList(OrderDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                Assertions.assertEquals(this!!.size, 1)
            }
    }
    @Test
    fun getAllOrder() {

        var url = UriComponentsBuilder.fromUriString(uri).toUriString()

        webTestClient.get().uri(url)

            .exchange()
            .expectStatus().isOk
            .expectBodyList(OrderDTO::class.java)
            .returnResult()
            .responseBody
            .run {
                Assertions.assertEquals(this!!.size, 1)
            }
    }
    @Test
    fun deleteOrder(){
        var url = UriComponentsBuilder.fromUriString(uri).queryParam("id", 2).toUriString()
        webTestClient.delete().uri(url)

            .exchange()
            .expectStatus().isOk
    }

}
