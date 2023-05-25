package com.android.smain.viewmodel

import androidx.lifecycle.ViewModel
import com.android.smain.repository.database.product.ProductDao
import com.android.smain.repository.database.product.ProductEntity

class ShopViewModel: ViewModel() {

    fun getProducts(): List<ProductEntity> {
        val products = arrayListOf<ProductEntity>()

        val product1 = ProductEntity(
            name = "BLUSA APLICACIÓN DE METAL\n",
            images = arrayListOf(
                "https://vertiche.mx/wp-content/uploads/2023/05/85017-HUESO-1.jpg",
                "https://vertiche.mx/wp-content/uploads/2023/05/85017-HUESO-2-595x893.jpg"
            ),
            description = "Blusa floral escote v con aplicación de metal y manga corta con amarre\n" +
                    "\n" +
                    "FIT REGULAR\n" +
                    "\n" +
                    "Composición: 100% Poliéster",
            price = 152.99)

        val product2 = ProductEntity(
            name = "BLUSA BIES ACCESORIO",
            images = arrayListOf(
                "https://vertiche.mx/wp-content/uploads/2023/05/85013-HUESO-1.jpg",
                "https://vertiche.mx/wp-content/uploads/2023/05/85013-HUESO-2-595x893.jpg"
            ),
            description = "Blusa floral escote redondo con abertura v aplicación de bies contraste con accesorio y manga corta\n" +
                    "\n" +
                    "FIT REGULAR\n" +
                    "\n" +
                    "Composición: 100% Poliéster",
            price = 101.99)

        val product3 = ProductEntity(
            name = "Nombre 3",
            images = arrayListOf("https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg"),
            description = "description product 3",
            price = 120.2)

        val product4 = ProductEntity(
            name = "Nombre 4",
            images = arrayListOf("https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_3.jpg"),
            description = "description product 4",
            price = 200.2)

        val product5 = ProductEntity(
            name = "Nombre 5",
            images = arrayListOf("https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg"),
            description = "description product 5",
            price = 320.2)

        val product6 = ProductEntity(
            name = "Nombre 6",
            images = arrayListOf("https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg"),
            description = "description product 6",
            price =210.2)

        products.add(product1)
        products.add(product2)
        products.add(product3)
        products.add(product4)
        products.add(product5)
        products.add(product6)

        val productDao = ProductDao()
        productDao.clear()
        productDao.insertAll(products)

        return products
    }
}