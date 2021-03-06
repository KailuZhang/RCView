package io.kailuzhang.github.rcview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.kailuzhang.github.rcview.data.ProductRepository

class ProductViewModelFactory(private val repository: ProductRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(repository) as T
    }
}