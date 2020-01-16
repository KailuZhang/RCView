package io.kailuzhang.github.rcview.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE favorite = 1 ORDER BY name")
    fun getFavorites(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProduct(productId: String): LiveData<Product>

    @Query("UPDATE products SET favorite = 1 WHERE id = :productId")
    suspend fun collectProduct(productId: String): Int

    @Query("UPDATE products SET favorite = 0 WHERE id = :productId")
    suspend fun cancelCollectProduct(productId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Product>)
}