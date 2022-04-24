package com.example.shoppingapi.servces

import com.example.shoppingapi.exceptions.exceptions.ItemDoesNotFoundException
import com.example.shoppingapi.exceptions.exceptions.UserNotFoundException
import com.example.shoppingapi.models.CurrencyEnum
import com.example.shoppingapi.models.Item
import com.example.shoppingapi.models.ItemCategory
import com.example.shoppingapi.models.dto.ItemDTO
import com.example.shoppingapi.repositories.ItemRepository
import com.example.shoppingapi.repositories.SellerRepository
import org.springframework.stereotype.Service

@Service
class ItemService(val itemRepository: ItemRepository, val sellerRepository: SellerRepository) {
    fun createItem(item: ItemDTO, id: Int): ItemDTO {
        return item.run {
           if(!sellerRepository.existsById(id)) throw UserNotFoundException("seller does not exit")

            itemRepository.save(
                Item(
                    null,
                    this.name,
                    ItemCategory.valueOf(this.category),
                    this.price,
                    CurrencyEnum.valueOf(this.currency),
                    sellerRepository.findById(id).get()
                )
            )
        }.let {
            ItemDTO(it.id, it.name, it.category.name, it.price, it.currency.name, it.seller?.id)
        }

    }

    fun getItems(id: Int): List<ItemDTO> {

        if (id.toInt() != 0) {
            if (!itemRepository.existsById(id)) throw ItemDoesNotFoundException()
            return listOf(itemRepository.findById(id).get())
                .map {
                    ItemDTO(
                        it.id,
                        it.name,
                        it.category.toString(),
                        it.price,
                        it.currency.toString(),
                        it.seller!!.id
                    )
                }
        }

        return itemRepository.findAll().toList()
            .map { ItemDTO(it.id, it.name, it.category.toString(), it.price, it.currency.toString(), it.seller!!.id) }
    }

    fun deleteItem(id: Int) {
        if (!itemRepository.existsById(id)) throw ItemDoesNotFoundException()
        itemRepository.deleteById(id)
    }

}
