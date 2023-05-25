package com.android.smain.repository.database.cart

import com.android.smain.repository.database.AbstractDao

class CartDao: AbstractDao<CartEntity>() {

    fun clear() {
        abstractBox<CartEntity>().removeAll()
    }

    fun removeProduct(id: Long) {
        abstractBox<CartEntity>().remove(id)
    }

    fun insert(cartEntity: CartEntity) {
        abstractBox<CartEntity>().put(cartEntity)
    }

    fun getCart(): List<CartEntity> {
        return abstractBox<CartEntity>().all
    }

    fun getTotalToPay(): Double {
        val cart = getCart()
        if (cart.isNullOrEmpty())
            return 0.0

        return cart.sumOf { it.price }
    }
}