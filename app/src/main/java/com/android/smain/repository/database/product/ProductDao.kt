package com.android.smain.repository.database.product

import com.android.smain.repository.database.AbstractDao

class ProductDao: AbstractDao<ProductEntity>() {

    fun clear() {
        abstractBox<ProductEntity>().removeAll()
    }

    fun removeItem(id: Long) {
        abstractBox<ProductEntity>().remove(id)
    }

    fun insert(productEntity: ProductEntity) {
        abstractBox<ProductEntity>().put(productEntity)
    }

    fun insertAll(products: List<ProductEntity>) {
        abstractBox<ProductEntity>().put(products)
    }

    fun getProducts(): List<ProductEntity> {
        return abstractBox<ProductEntity>().all
    }
}