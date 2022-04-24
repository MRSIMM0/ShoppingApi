package com.example.shoppingapi.servces

import com.example.shoppingapi.exceptions.exceptions.UserNotFoundException
import com.example.shoppingapi.models.UserOrder
import com.example.shoppingapi.models.dto.OrderDTO
import com.example.shoppingapi.repositories.CustomerRepository
import com.example.shoppingapi.repositories.ItemRepository
import com.example.shoppingapi.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(

    var orderRepository: OrderRepository,

    var itemRepository: ItemRepository,

    var customerRepository: CustomerRepository

) {
    fun createOrder(orderDTO: OrderDTO, userId: Int): OrderDTO? {
        return orderDTO.run {

            val items = if (orderDTO.itemsId!!.first() != null) {
                orderDTO.itemsId?.map { it -> itemRepository.findById(it!!).get() }
            } else {
                listOf(null)
            }
            orderRepository.save(
                UserOrder(
                    this.id, this.date, this.orderStatus, items, customerRepository.findById(userId).get()
                )
            )
        }.let {
            OrderDTO(it.id, it.date, it.orderStatus, it.items!!.map { it -> it?.id }, it.user.id!!)
        }
    }

    fun getOrder(id: Int): List<OrderDTO> {
        if (id != 0) {
            if(!orderRepository.existsById(id)) throw UserNotFoundException("User does not exist")
            return listOf(orderRepository.findById(id).get().let { OrderDTO(it.id,it.date,it.orderStatus,it.items!!.map { i -> i?.id },it.user.id) })
        }
        return orderRepository.findAll()
            .map { it -> OrderDTO(it.id, it.date, it.orderStatus, it.items!!.map { i -> i?.id }, it.user.id) }



    }

    fun deleteOrder(id: Int) {
        if(!orderRepository.existsById(id)) throw UserNotFoundException("User does not exist")
        orderRepository.deleteById(id)
    }
}