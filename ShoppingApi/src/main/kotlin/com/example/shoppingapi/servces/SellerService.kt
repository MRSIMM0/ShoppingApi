package com.example.shoppingapi.servces

import com.example.shoppingapi.exceptions.exceptions.UserNotFoundException
import com.example.shoppingapi.models.dto.SellerDTO
import com.example.shoppingapi.models.users.Seller
import com.example.shoppingapi.repositories.ItemRepository
import com.example.shoppingapi.repositories.SellerRepository
import org.springframework.stereotype.Service

@Service
class SellerService(val sellerRepository: SellerRepository, val itemRepository: ItemRepository) {
    fun createSeller(sellerDTO: SellerDTO): SellerDTO {
        return sellerDTO.run {
            sellerRepository.save(
                Seller(
                    this.username,
                    this.password,
                    this.itemsId?.map { it -> itemRepository.findById(it!!.toInt()).get()})
            )
        }.let {
            SellerDTO(it.id, it.username, it.password, it.items?.map { it -> it?.id })
        }
    }

    fun getAll(id: Int): List<SellerDTO> {

        if (id.toInt() != 0) {
            if (!sellerRepository.existsById(id)) throw UserNotFoundException("Seller does not exist")
            return listOf(
                sellerRepository.findById(id).get()
                    .let { SellerDTO(it.id, it.username, it.password, it.items?.map { it -> it?.id }) })
        }

       return sellerRepository.findAll().toList().map{
           it ->  SellerDTO(it.id,it.username,it.password,it.items?.map { it -> it?.id })
       }

    }

    fun deleteSeller(id: Int) {
        if (!sellerRepository.existsById(id)) throw UserNotFoundException("Seller does not exist")
        sellerRepository.deleteById(id)
    }

}
