package com.android.smain.viewmodel

import androidx.lifecycle.ViewModel
import com.android.smain.repository.database.address.AddressDao
import com.android.smain.repository.database.address.AddressEntity

class AddAddressViewModel: ViewModel() {

    fun addAddress(street: String, number: String, city: String, zipCode: String) {
        val addressDao = AddressDao()
        val addressEntity = AddressEntity(
            street = street,
            number = number,
            city = city,
            zipcode = zipCode
        )
        addressDao.insert(addressEntity)
    }
}