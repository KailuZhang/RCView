package io.kailuzhang.github.rcview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.kailuzhang.github.rcview.data.Product
import io.kailuzhang.github.rcview.data.ProductRepository

class FavoriteViewModel(val productRepository: ProductRepository): ViewModel() {

    val favoriteList : LiveData<List<Product>> = productRepository.getFavorites()
}