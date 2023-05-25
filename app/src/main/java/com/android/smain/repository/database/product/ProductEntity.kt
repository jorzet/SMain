package com.android.smain.repository.database.product

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ProductEntity(
    @Id(assignable = true)
    var id: Long = 0,
    var name: String,
    var images: List<String>,
    var description: String,
    var price: Double
): java.io.Serializable