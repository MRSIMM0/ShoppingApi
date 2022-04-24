package com.example.shoppingapi.servces.handlers

import com.example.shoppingapi.exceptions.exceptions.InvalidArgumentException
import com.example.shoppingapi.models.dto.CustomerAndSellerDTO
import com.example.shoppingapi.models.dto.CustomerDTO
import com.example.shoppingapi.models.dto.SellerDTO
import com.example.shoppingapi.servces.CustomerService
import com.example.shoppingapi.servces.SellerService
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Component

@Component
class HandleUser {


    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var sellerService: SellerService

    fun customerSellerFactory(type: String, customerAndSellerDTO: CustomerAndSellerDTO): Any {
        return when (type) {
            "seller" -> {
                sellerService.createSeller(objToCustomerSeller(customerAndSellerDTO))
            }
            "customer" -> {
                customerService.createCustomer(objToCustomerMapper(customerAndSellerDTO))
            }
            else -> {
                throw InvalidArgumentException("$type type is invalid")
            }
        }
    }

    fun getAllByType(type: String, id: Int):Any?{
        return when(type){
            "seller"-> getAllSellers(id)
            "customer" -> getAllCustomers(id)
            else -> throw InvalidArgumentException("$type type is invalid")
        }
    }

    fun delete(type: String, id: Int) {
        when (type){
            "seller"-> deleteSeller(id)
            "customer" -> deleteCustomer(id)
            else -> throw InvalidArgumentException("$type type is invalid")
        }
    }

    private fun deleteCustomer(id: Int) {
        customerService.deleteCustomer(id)
    }

    private fun deleteSeller(id: Int) {
        sellerService.deleteSeller(id)
    }

    private fun objToCustomerMapper(customerAndSellerDTO: CustomerAndSellerDTO): CustomerDTO {
        return customerAndSellerDTO.let {
            CustomerDTO(it.id, it.username, it.password, it.ordersOrItemsId)
        }
    }

    private fun objToCustomerSeller(customerAndSellerDTO: CustomerAndSellerDTO): SellerDTO {
        return customerAndSellerDTO.let {
            SellerDTO(it.id, it.username, it.password, it.ordersOrItemsId)
        }
    }


    private fun getAllCustomers(id: Int): List<CustomerDTO>{
        return customerService.getAll(id)
    }

    private fun getAllSellers(id: Int) : List<SellerDTO>{
        return sellerService.getAll(id)
    }



}



