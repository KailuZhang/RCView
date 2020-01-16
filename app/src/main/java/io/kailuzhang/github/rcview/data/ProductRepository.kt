package io.kailuzhang.github.rcview.data

class ProductRepository private constructor(private val productDao: ProductDao) {

    fun getProducts() = productDao.getProducts()

    fun getFavorites() = productDao.getFavorites()

    fun getProduct(productId: String) = productDao.getProduct(productId)

    suspend fun collectProduct(productId: String) = productDao.collectProduct(productId)

    suspend fun cancelCollectProduct(productId: String) = productDao.cancelCollectProduct(productId)

    companion object {

        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(productDao: ProductDao): ProductRepository {
            return instance ?: synchronized(this) {
                instance ?: ProductRepository(productDao).also { instance = it }
            }
        }
    }
}