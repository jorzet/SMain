package com.android.smain.ui.payment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.smain.databinding.ActivityAddCardBinding
import com.android.smain.viewmodel.AddCardViewModel

class AddCardActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding
    private lateinit var viewModel: AddCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AddCardViewModel::class.java]

        setUpListsners()
        setContentView(binding.root)
    }

    private fun setUpListsners() {
        binding.btnAddCard.setOnClickListener {
            val titular = binding.etTitular.text.toString()
            val number = binding.etCardNumber.text.toString()
            val expireDate = binding.metExpire.text.toString()
            val cvv = binding.etSecureCode.text.toString()

            if (!titular.isNullOrEmpty()) {
                if (!number.isNullOrEmpty() && number.length == 16) {
                    if (!expireDate.isNullOrEmpty()) {
                        if (!cvv.isNullOrEmpty()) {
                            viewModel.saveCard(titular, number, expireDate, cvv)
                            setResult(500)
                            finish()
                        } else {
                            showError("Debe ingresar el codigo de seguridad")
                        }
                    } else {
                        showError("Debe ingresar la fecha de expiracion")
                    }
                } else {
                    showError("Debe ingresar el numero de tarjeta valido")
                }
            } else {
                showError("Debe ingresar el nombre del titular")
            }

        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}