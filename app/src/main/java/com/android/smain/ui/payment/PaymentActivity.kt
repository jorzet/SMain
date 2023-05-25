package com.android.smain.ui.payment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.smain.databinding.ActivityPaymentBinding
import com.android.smain.model.PaymentViewState
import com.android.smain.repository.database.address.AddressEntity
import com.android.smain.ui.address.AddAddressActivity
import com.android.smain.viewmodel.PaymentViewModel

class PaymentActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var viewModel: PaymentViewModel

    companion object {
        const val TOTAL_PRICE = "total_price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[PaymentViewModel::class.java]

        setUpView()
        setUpListeners()
        setUpObservers()
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 500) {
            val address = viewModel.getAddress()
            if (address != null) {
                setUpAddess(address)
            } else {
                showAddAddressActivity()
            }
        } else if (resultCode == 700) {
            setUpView()
        }
    }

    private fun setUpView() {
        val totalPrice = intent.extras?.getDouble(TOTAL_PRICE)
        val formattedNumber = String.format("Total a pagar $%.2f MXN", totalPrice)
        binding.tvTotalPay.text = formattedNumber

        val card = viewModel.getCard();
        if (card != null) {
            setUpCard(card)

            val address = viewModel.getAddress()
            if (address != null) {
                setUpAddess(address)
            } else {
                showAddAddressActivity()
            }
        } else {
            showAddCardActivity()
        }
    }

    private fun setUpListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.btnPayNow.setOnClickListener {
            viewModel.pay()
        }
    }

    private fun setUpObservers() {
        viewModel.paymentViewState.observe(this, ::renderViewState)
    }

    private fun renderViewState(paymentViewState: PaymentViewState) {
        when(paymentViewState) {
            is PaymentViewState.PaymentSuccess -> {
                Toast.makeText(this, paymentViewState.message, Toast.LENGTH_SHORT).show()
                goHome()
            }
            is PaymentViewState.PaymentError -> {
                Toast.makeText(this, paymentViewState.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goHome() {
        setResult(300)
        finish()
    }

    private fun showAddCardActivity() {
        val intent = Intent(this, AddCardActivity::class.java)
        startActivityForResult(intent, 400)
    }

    private fun showAddAddressActivity() {
        val intent = Intent(this, AddAddressActivity::class.java)
        startActivityForResult(intent, 600)
    }

    private fun setUpCard(card: String) {
        binding.tvCardNumber.text = card
    }

    private fun setUpAddess(addressEntity: AddressEntity) {
        binding.tvStreet.text = "Calle: ${addressEntity.street}"
        binding.tvNumber.text = "Numero: ${addressEntity.number}"
        binding.tvCity.text = "Ciudad: ${addressEntity.city}"
        binding.tvZipcode.text = "Codigo postal: ${addressEntity.zipcode}"
    }

}