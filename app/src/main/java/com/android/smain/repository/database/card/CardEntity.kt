package com.android.smain.repository.database.card

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class CardEntity (
    @Id(assignable = true)
    var id: Long = 0,
    var titular: String,
    var number: String,
    var expireDate: String,
    var cvv: String
)