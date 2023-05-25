package com.android.smain.ui.address

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.smain.databinding.ActivityAddAddressBinding
import com.android.smain.viewmodel.AddAddressViewModel

class AddAddressActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddAddressBinding
    private lateinit var viewModel: AddAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AddAddressViewModel::class.java]

        setUpListeners()
        setContentView(binding.root)
    }

    private fun setUpListeners() {
        binding.btnSaveAddress.setOnClickListener {
            val street = binding.etStreet.text.toString()
            val number = binding.etNumber.text.toString()
            val city = binding.etCity.text.toString()
            val zipCode = binding.etZipcode.text.toString()

            if (!street.isNullOrEmpty()) {
                if (!number.isNullOrEmpty()) {
                    if (!city.isNullOrEmpty()) {
                        if (!zipCode.isNullOrEmpty()) {
                            viewModel.addAddress(street, number, city, zipCode)
                            setResult(700)
                            finish()
                        } else {
                            showError("Debe ingresar el codigo postal")
                        }
                    } else {
                        showError("Debe ingresar la ciudad")
                    }
                } else {
                    showError("Debe ingresar el numero de casa")
                }
            } else {
                showError("Debe ingresar la calle")
            }
        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}