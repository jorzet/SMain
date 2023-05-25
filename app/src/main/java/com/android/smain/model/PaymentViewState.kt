package com.android.smain.model

sealed class PaymentViewState {
    data class PaymentSuccess(val message: String): PaymentViewState()
    data class PaymentError(val error: String): PaymentViewState()
}
