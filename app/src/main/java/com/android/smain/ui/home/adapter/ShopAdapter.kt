package com.android.smain.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.smain.databinding.ItemShopBinding
import com.android.smain.repository.database.product.ProductEntity
import com.squareup.picasso.Picasso

class ShopAdapter(val products: List<ProductEntity>, val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(product: ProductEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemShopBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], onItemClickListener)
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(val binding: ItemShopBinding):  RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity, onItemClickListener: OnItemClickListener) {

            if (!product.images.isNullOrEmpty()) {
                val image = product.images[0]
                Picasso.get().load(image).into(binding.ivItem)
            }

            binding.tvName.text = product.name
            binding.tvPrice.text = product.price.toString()

            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(product)
            }
        }
    }
}