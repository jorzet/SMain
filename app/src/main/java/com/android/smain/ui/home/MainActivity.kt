package com.android.smain.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.smain.R
import com.android.smain.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initView()
        setUpListeners()
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 300) {
            binding.tlMenu.getTabAt(0)?.select()
        }
    }

    private fun initView() {
        val shopTab = binding.tlMenu.newTab()
        shopTab.text = "Shop"
        shopTab.setIcon(R.drawable.shop_icon)

        val cartTab = binding.tlMenu.newTab()
        cartTab.text = "Cart"
        cartTab.setIcon(R.drawable.cart_icon)

        binding.tlMenu.addTab(shopTab, true)
        binding.tlMenu.addTab(cartTab, false)

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(binding.flSmain.id, ShopFragment())
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }

    private fun setUpListeners() {
        binding.tlMenu.setOnTabSelectedListener(object: OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val fragment: Fragment = when (tab.position) {
                        0 -> {
                            ShopFragment()
                        }
                        1-> {
                            CartFragment()
                        }
                        else -> {CartFragment()}
                    }
                    val fm: FragmentManager = supportFragmentManager
                    val ft: FragmentTransaction = fm.beginTransaction()
                    ft.replace(binding.flSmain.id, fragment)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ft.commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}