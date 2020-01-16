package io.kailuzhang.github.rcview.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import io.kailuzhang.github.rcview.data.AppDatabase
import io.kailuzhang.github.rcview.data.Product
import kotlinx.coroutines.coroutineScope

const val PRODUCT_DATA_FILENAME = "product.json"

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PRODUCT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Product>>() {}.type
                    val plantList: List<Product> = Gson().fromJson(jsonReader, plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.productDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(this::class.java.simpleName, "Error seeding database", ex)
            Result.failure()
        }
    }
}