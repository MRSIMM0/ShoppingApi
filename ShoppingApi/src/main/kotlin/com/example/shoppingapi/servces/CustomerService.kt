package com.example.shoppingapi.servces

import com.example.shoppingapi.exceptions.exceptions.UserNotFoundException
import com.example.shoppingapi.models.dto.CustomerDTO
import com.example.shoppingapi.models.users.Customer
import com.example.shoppingapi.repositories.CustomerRepository
import com.example.shoppingapi.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository, val orderRepository: OrderRepository) {

    fun createCustomer(customerDTO: CustomerDTO): CustomerDTO {

        return customerDTO.run {
            customerRepository.save(
                Customer(
                    this.username,
                    this.password,
                    this.ordersId?.map { it -> orderRepository.findById(it!!.toInt()).get() })
            )
        }.let {
            CustomerDTO(it.id, it.username, it.password, it.orderList?.map { it -> it?.id })
        }
    }

    fun getAll(id: Int): List<CustomerDTO> {
        if (id != 0) {
            if (!customerRepository.existsById(id)) throw UserNotFoundException("Customer does not exist")
            return listOf(
                customerRepository.findById(id).get()
                    .let { CustomerDTO(it.id, it.username, it.password, it.orderList?.map { it -> it?.id }) })
        }
        return customerRepository.findAll().toList().map { it ->
            CustomerDTO(it.id, it.username, it.password, it.orderList?.map { it -> it?.id })
        }
    }

    fun deleteCustomer(id: Int) {
        if (!customerRepository.existsById(id)) throw UserNotFoundException("Customer does not exist")
        customerRepository.deleteById(id)
    }
}