package io.kailuzhang.github.rcview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.kailuzhang.github.rcview.adapters.ProductListAdapter
import io.kailuzhang.github.rcview.databinding.FavoriteListFragmentBinding
import io.kailuzhang.github.rcview.utilities.InjectorUtils
import io.kailuzhang.github.rcview.viewmodels.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels {
        InjectorUtils.provideFavoriteListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FavoriteListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.favorite_list_fragment,
            container,
            false
        )

        val adapter = object : ProductListAdapter(viewModel.productRepository) {
            override val layoutId: Int = R.layout.favorite_item
        }
        binding.favoriteList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: ProductListAdapter) {
        viewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
    }
}