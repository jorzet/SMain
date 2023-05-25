package com.android.smain.viewmodel

import androidx.lifecycle.ViewModel
import com.android.smain.repository.database.cart.CartDao
import com.android.smain.repository.database.cart.CartEntity

class CartViewModel: ViewModel() {

    fun loadCart(): List<CartEntity> {
        val cartDao = CartDao()
        return cartDao.getCart()
    }

    fun deleteItem(cartEntity: CartEntity) {
        val cartDao = CartDao()
        cartDao.removeProduct(cartEntity.id)
    }

    fun getTotalToPay(): Double {
        val cartDao = CartDao()
        return cartDao.getTotalToPay()
    }
}