package io.kailuzhang.github.rcview.utilities

import android.content.Context
import io.kailuzhang.github.rcview.data.AppDatabase
import io.kailuzhang.github.rcview.data.ProductRepository
import io.kailuzhang.github.rcview.viewmodels.FavoriteViewModelFactory
import io.kailuzhang.github.rcview.viewmodels.ProductViewModelFactory

object InjectorUtils {

    private fun getProductsRepository(context: Context): ProductRepository {
        return ProductRepository.getInstance(AppDatabase.getInstance(context).productDao())
    }

    fun provideFavoriteListViewModelFactory(context: Context): FavoriteViewModelFactory {
        val repository = getProductsRepository(context)
        return FavoriteViewModelFactory(repository)
    }

    fun provideProductListViewModelFactory(context: Context): ProductViewModelFactory {
        val repository = getProductsRepository(context)
        return ProductViewModelFactory(repository)
    }
}