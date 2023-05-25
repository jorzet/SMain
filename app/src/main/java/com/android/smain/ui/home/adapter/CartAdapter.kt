package com.android.smain.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.smain.databinding.ItemCartBinding
import com.android.smain.repository.database.cart.CartEntity
import com.squareup.picasso.Picasso

class CartAdapter(
    val products: List<CartEntity>,
    val onItemDeleteClickListener: OnItemDeleteClickListener
    ): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    interface OnItemDeleteClickListener {
        fun onItemDelete(cartEntity: CartEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], onItemDeleteClickListener)
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(val binding: ItemCartBinding):  RecyclerView.ViewHolder(binding.root) {
        fun bind(cartEntity: CartEntity, onItemDeleteClickListener: OnItemDeleteClickListener) {

            if (!cartEntity.images.isNullOrEmpty()) {
                val image = cartEntity.images[0]
                Picasso.get().load(image).into(binding.ivProduct)
            }

            binding.tvName.text = cartEntity.name
            binding.btnRemoveItem.setOnClickListener {
                onItemDeleteClickListener.onItemDelete(cartEntity)
            }
        }
    }
}