package com.android.smain.repository.database.cart

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class CartEntity(
    @Id(assignable = true)
    var id: Long = 0,
    var name: String,
    var images: List<String>,
    var description: String,
    var price: Double
): java.io.Serializable