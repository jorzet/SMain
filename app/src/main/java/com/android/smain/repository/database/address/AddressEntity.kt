package com.android.smain.repository.database.address

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class AddressEntity(
    @Id(assignable = true)
    var id: Long = 0,
    var street: String,
    var number: String,
    var city: String,
    var zipcode: String
)