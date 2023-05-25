package com.android.smain.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.smain.databinding.ShopFragmentBinding
import com.android.smain.repository.database.product.ProductEntity
import com.android.smain.ui.cardview.ImageListener
import com.android.smain.ui.home.adapter.ShopAdapter
import com.android.smain.ui.item.ItemActivity
import com.android.smain.viewmodel.ShopViewModel
import com.squareup.picasso.Picasso

class ShopFragment: Fragment() {

    private lateinit var binding: ShopFragmentBinding
    private lateinit var shopAdapter: ShopAdapter
    private lateinit var viewModel: ShopViewModel

    var sampleImages = arrayOf(
        "https://vertiche.mx/wp-content/uploads/2023/05/vertiche-slider-promo-20-20230512.jpg",
        "https://vertiche.mx/wp-content/uploads/2023/05/vertiche-laola-slider-20230511.jpg",
        "https://vertiche.mx/wp-content/uploads/2023/05/vertiche-slider-promo-15-2023051.jpg"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShopFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]
        initView()
        return binding.root
    }

    private fun initView() {
        binding.carouselView.pageCount = sampleImages.size
        binding.carouselView.setImageListener(imageListener)

        val products = viewModel.getProducts()
        shopAdapter = ShopAdapter(products, itemShopClickListener)
        val layoutManager = GridLayoutManager(requireContext(), 2) // Set the number of columns in the grid
        binding.rvItemList.layoutManager = layoutManager
        binding.rvItemList.adapter = shopAdapter
    }

    private var imageListener: ImageListener = ImageListener { position, imageView ->
        Picasso.get().load(sampleImages[position]).into(imageView)
    }

    private val itemShopClickListener = object: ShopAdapter.OnItemClickListener {
        override fun onItemClick(product: ProductEntity) {
            showItemActivity(product)
        }
    }

    private fun showItemActivity(product: ProductEntity) {
        val intent = Intent(requireActivity(), ItemActivity::class.java)
        intent.putExtra(ItemActivity.PRODUCT, product)
        startActivity(intent)
    }
}