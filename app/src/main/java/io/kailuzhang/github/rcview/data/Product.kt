package io.kailuzhang.github.rcview.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey @ColumnInfo(name = "id") val productId: String,
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    val favorite: Boolean
)