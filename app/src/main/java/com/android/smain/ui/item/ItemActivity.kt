package com.android.smain.ui.item

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.smain.databinding.ActivityItemDescriptionBinding
import com.android.smain.repository.database.product.ProductEntity
import com.android.smain.ui.payment.PaymentActivity
import com.android.smain.viewmodel.ItemVewModel
import com.squareup.picasso.Picasso

class ItemActivity: AppCompatActivity() {

    companion object {
        const val PRODUCT = "PRODUCT"
    }

    private lateinit var binding: ActivityItemDescriptionBinding
    private lateinit var viewModel: ItemVewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDescriptionBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ItemVewModel::class.java]

        val product = intent.extras?.getSerializable(PRODUCT) as ProductEntity
        initView(product)
        setUpListeners()
        setContentView(binding.root)
    }

    private fun initView(product: ProductEntity?) {
        product?.let {
            binding.carouselView.pageCount = product.images.size
            binding.carouselView.setImageListener { position, imageView ->
                Picasso.get().load(product.images[position]).into(imageView)
            }

            binding.tvName.text = product.name
            binding.tvPrice.text = product.price.toString()
            binding.tvDescription.text = product.description
        }
    }

    private fun setUpListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.btnAddCart.setOnClickListener {
            val product = intent.extras?.getSerializable(PRODUCT) as ProductEntity
            viewModel.addToCart(product)

            Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
        }
        binding.btnPayNow.setOnClickListener {
            showPaymentActivity()
        }
    }

    private fun showPaymentActivity() {
        val product = intent.extras?.getSerializable(PRODUCT) as ProductEntity

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PaymentActivity.TOTAL_PRICE, product.price)
        startActivityForResult(intent, 100)
    }
}