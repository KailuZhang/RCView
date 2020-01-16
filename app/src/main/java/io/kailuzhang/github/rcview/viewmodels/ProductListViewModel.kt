package io.kailuzhang.github.rcview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.kailuzhang.github.rcview.data.Product
import io.kailuzhang.github.rcview.data.ProductRepository

class ProductListViewModel(val productRepository: ProductRepository) : ViewModel() {

    val productList: LiveData<List<Product>> = productRepository.getProducts()
}