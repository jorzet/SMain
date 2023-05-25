package com.android.smain.repository.database.card

import com.android.smain.repository.database.AbstractDao

class CardDao: AbstractDao<CardEntity>() {

    fun clear() {
        abstractBox<CardEntity>().removeAll()
    }

    fun insert(cardEntity: CardEntity) {
        abstractBox<CardEntity>().put(cardEntity)
    }

    fun getCardData(): CardEntity? {
        val result = abstractBox<CardEntity>().all
        return if (result.isNullOrEmpty()) null else result[0]
    }
}