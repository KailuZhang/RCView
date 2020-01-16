package io.kailuzhang.github.rcview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.kailuzhang.github.rcview.adapters.ProductListAdapter
import io.kailuzhang.github.rcview.databinding.ProductListFragmentBinding
import io.kailuzhang.github.rcview.utilities.InjectorUtils
import io.kailuzhang.github.rcview.viewmodels.ProductListViewModel

class ProductListFragment : Fragment() {

    private val viewModel: ProductListViewModel by viewModels {
        InjectorUtils.provideProductListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProductListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.product_list_fragment,
            container,
            false
        )

        val adapter = object : ProductListAdapter(viewModel.productRepository) {
            override val layoutId: Int = R.layout.product_item
        }
        binding.productList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: ProductListAdapter) {
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
    }
}