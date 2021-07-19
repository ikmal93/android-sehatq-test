package com.ikmal.androidsehatqtest.features.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ikmal.androidsehatqtest.R
import com.ikmal.androidsehatqtest.databinding.ActivityHomeBinding
import com.ikmal.androidsehatqtest.features.cart.CartFragment
import com.ikmal.androidsehatqtest.features.feed.FeedFragment
import com.ikmal.androidsehatqtest.features.home.view.HomeFragment
import com.ikmal.androidsehatqtest.features.profile.view.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setView()
        setListener()
    }

    private fun initViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setView() {
        val homeFragment = HomeFragment()
        initFragment(homeFragment)
    }

    private fun initFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.cl_base_constraint, fragment)
            .commit()
    }

    private fun setListener() {
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val homeFragment = HomeFragment()
                    initFragment(homeFragment)
                }
                R.id.nav_feed -> {
                    val feedFragment = FeedFragment()
                    initFragment(feedFragment)
                }
                R.id.nav_cart -> {
                    val cartFragment = CartFragment()
                    initFragment(cartFragment)
                }
                R.id.nav_profile -> {
                    val profileFragment = ProfileFragment()
                    initFragment(profileFragment)
                }
            }
            true
        }
    }
}