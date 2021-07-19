package com.ikmal.androidsehatqtest.features.home.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikmal.androidsehatqtest.core.constant.IntentKey.PRODUCT
import com.ikmal.androidsehatqtest.core.constant.IntentKey.PRODUCT_LIST
import com.ikmal.androidsehatqtest.core.database.AppDatabase
import com.ikmal.androidsehatqtest.core.database.DatabaseBuilder
import com.ikmal.androidsehatqtest.core.network.ApiBuilder
import com.ikmal.androidsehatqtest.core.utils.Status.*
import com.ikmal.androidsehatqtest.core.viewmodel.ViewModelFactory
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.databinding.FragmentHomeBinding
import com.ikmal.androidsehatqtest.features.home.view.adapter.CategoryAdapter
import com.ikmal.androidsehatqtest.features.home.view.adapter.ProductAdapter
import com.ikmal.androidsehatqtest.features.home.viewmodel.HomeViewModel
import com.ikmal.androidsehatqtest.features.product.detail.ProductDetailActivity
import com.ikmal.androidsehatqtest.features.product.search.view.ProductSearchActivity

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    private lateinit var productList: List<ProductPromo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        ).get(HomeViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getData().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        resource.data?.let { dataResponse ->
                            binding?.apply {
                                setLoading(false)

                                productList = dataResponse[0].data.productPromo

                                homeCategoryRecycler.setHasFixedSize(true)
                                homeCategoryRecycler.layoutManager = LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                dataResponse[0].data.category.let { categories ->
                                    categoryAdapter = CategoryAdapter(categories) {
                                        Toast.makeText(
                                            requireContext(),
                                            "${it.name}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                homeCategoryRecycler.adapter = categoryAdapter

                                homeProductRecycler.setHasFixedSize(true)
                                homeProductRecycler.layoutManager =
                                    LinearLayoutManager(requireContext())
                                dataResponse[0].data.productPromo.let { products ->
                                    productAdapter = ProductAdapter(products) { product ->
                                        startActivity(
                                            Intent(
                                                requireContext(),
                                                ProductDetailActivity::class.java
                                            )
                                                .putExtra(PRODUCT, product)
                                        )
                                    }
                                }
                                homeProductRecycler.adapter = productAdapter
                            }
                        }
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                        setLoading(false)
                    }
                    LOADING -> {
                        setLoading(true)
                    }
                }
            }
        })
    }

    private fun setupListener() {
        binding?.apply {
            homeSearchCard.setOnClickListener {
                startActivity(
                    Intent(requireContext(), ProductSearchActivity::class.java)
                )
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.apply {
                homeProgressBar.visibility = View.VISIBLE
                homeToolbar.visibility = View.GONE
                homeNested.visibility = View.GONE
            }
        } else {
            binding?.apply {
                homeProgressBar.visibility = View.GONE
                homeToolbar.visibility = View.VISIBLE
                homeNested.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}