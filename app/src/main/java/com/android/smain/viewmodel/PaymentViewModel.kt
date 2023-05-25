package com.android.smain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.smain.model.PaymentViewState
import com.android.smain.repository.database.address.AddressDao
import com.android.smain.repository.database.address.AddressEntity
import com.android.smain.repository.database.card.CardDao
import com.android.smain.repository.database.cart.CartDao

class PaymentViewModel: ViewModel() {

    val paymentViewState = MutableLiveData<PaymentViewState>()

    fun getCard(): String? {
        val cardDao = CardDao()
        val cardData = cardDao.getCardData()
        if (cardData != null) {
            return "**** ${
                cardData.number.substring(
                    cardData.number.length - 4,
                    cardData.number.length
                )
            }"
        } else return null
    }

    fun getAddress(): AddressEntity? {
        val addressDao = AddressDao()
        return addressDao.getAddress()
    }

    fun pay() {
        clearCart()
        paymentViewState.value = PaymentViewState.PaymentSuccess("Pago realizado con exito!!")
    }

    fun clearCart() {
        val cartDao = CartDao()
        cartDao.clear()
    }
}