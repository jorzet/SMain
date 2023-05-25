package com.android.smain.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.smain.databinding.CartFragmentBinding
import com.android.smain.repository.database.cart.CartEntity
import com.android.smain.ui.home.adapter.CartAdapter
import com.android.smain.ui.payment.PaymentActivity
import com.android.smain.viewmodel.CartViewModel


class CartFragment: Fragment() {

    private lateinit var binding: CartFragmentBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setUpListeners()
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val products = viewModel.loadCart()
        if (products.isNullOrEmpty()) {
            binding.llEmptyCart.visibility = View.VISIBLE
            binding.rlCartItems.visibility = View.GONE
        } else {
            binding.rlCartItems.visibility = View.VISIBLE
            binding.llEmptyCart.visibility = View.GONE
            adapter = CartAdapter(products, object : CartAdapter.OnItemDeleteClickListener {
                override fun onItemDelete(cartEntity: CartEntity) {
                    viewModel.deleteItem(cartEntity)
                    initRecyclerView()
                    Toast.makeText(
                        requireContext(),
                        "Se elimino ${cartEntity.name} del carrito",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            binding.rvItemList.adapter = adapter
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
            binding.rvItemList.layoutManager = layoutManager
        }
    }

    private fun setUpListeners() {
        binding.btnPayNow.setOnClickListener {
            showPaymentActivity()
        }
    }

    private fun showPaymentActivity() {
        val totalPrice = viewModel.getTotalToPay()
        val intent = Intent(requireActivity(), PaymentActivity::class.java)
        intent.putExtra(PaymentActivity.TOTAL_PRICE, totalPrice)
        startActivityForResult(intent, 200)
    }

}