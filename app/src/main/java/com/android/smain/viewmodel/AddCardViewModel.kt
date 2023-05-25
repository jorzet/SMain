package com.android.smain.viewmodel

import androidx.lifecycle.ViewModel
import com.android.smain.repository.database.card.CardDao
import com.android.smain.repository.database.card.CardEntity

class AddCardViewModel: ViewModel() {

    fun saveCard(titular: String, number: String, expireDate: String, cvv: String) {
        val cardDao = CardDao()
        val cardEntity = CardEntity(
            titular = titular,
            number = number,
            expireDate = expireDate,
            cvv = cvv
        )
        cardDao.insert(cardEntity)
    }
}