package com.ikmal.androidsehatqtest.features.profile.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikmal.androidsehatqtest.core.constant.IntentKey
import com.ikmal.androidsehatqtest.core.database.DatabaseBuilder
import com.ikmal.androidsehatqtest.core.network.ApiBuilder
import com.ikmal.androidsehatqtest.core.utils.Status
import com.ikmal.androidsehatqtest.core.viewmodel.ViewModelFactory
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.databinding.FragmentProfileBinding
import com.ikmal.androidsehatqtest.features.product.detail.ProductDetailActivity
import com.ikmal.androidsehatqtest.features.profile.view.adapter.ProfileAdapter
import com.ikmal.androidsehatqtest.features.profile.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var viewModel: ProfileViewModel

    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObservers()
        setupListener()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiBuilder.apiService, DatabaseBuilder.getInstance(requireContext()))
        ).get(ProfileViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getHistories().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { histories ->
                            binding?.apply {
                                if (histories.isNotEmpty()) {
                                    baseEmptyTrx.visibility = View.GONE
                                    profileRecycler.visibility = View.VISIBLE

                                    profileRecycler.setHasFixedSize(true)
                                    profileRecycler.layoutManager =
                                        LinearLayoutManager(requireContext())
                                    profileAdapter = ProfileAdapter(histories) {
                                        startActivity(
                                            Intent(
                                                requireContext(),
                                                ProductDetailActivity::class.java
                                            )
                                                .putExtra(
                                                    IntentKey.PRODUCT, ProductPromo(
                                                        id = it.id,
                                                        description = it.description,
                                                        imageUrl = it.imageUrl,
                                                        loved = it.loved,
                                                        price = it.price,
                                                        title = it.title
                                                    )
                                                )
                                        )
                                    }
                                    profileRecycler.adapter = profileAdapter
                                } else {
                                    baseEmptyTrx.visibility = View.VISIBLE
                                    profileRecycler.visibility = View.GONE
                                }
                            }

                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding?.apply {

                        }
                    }
                }
            }
        })
    }

    private fun setupListener() {
        binding?.apply {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}