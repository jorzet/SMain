package com.android.smain.viewmodel

import androidx.lifecycle.ViewModel
import com.android.smain.repository.database.cart.CartDao
import com.android.smain.repository.database.cart.CartEntity
import com.android.smain.repository.database.product.ProductEntity

class ItemVewModel: ViewModel() {

    fun addToCart(productEntity: ProductEntity) {
        val cartEntity = CartEntity(
            name = productEntity.name,
            description = productEntity.description,
            images = productEntity.images,
            price = productEntity.price
        )

        val cartDao = CartDao()
        cartDao.insert(cartEntity)
    }
}