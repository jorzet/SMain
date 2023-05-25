package com.android.smain.repository.database.address

import com.android.smain.repository.database.AbstractDao

class AddressDao: AbstractDao<AddressEntity>() {

    fun clear() {
        abstractBox<AddressEntity>().removeAll()
    }

    fun insert(addressEntity: AddressEntity) {
        abstractBox<AddressEntity>().put(addressEntity)
    }

    fun getAddress(): AddressEntity? {
        val result = abstractBox<AddressEntity>().all
        return if (result.isNullOrEmpty()) null else result[0]
    }
}