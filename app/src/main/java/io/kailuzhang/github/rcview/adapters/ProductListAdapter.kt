package io.kailuzhang.github.rcview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.kailuzhang.github.rcview.BR
import io.kailuzhang.github.rcview.R
import io.kailuzhang.github.rcview.data.Product
import io.kailuzhang.github.rcview.data.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class ProductListAdapter(private val productRepository: ProductRepository) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    abstract val layoutId: Int

    var items: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            ),
            onFavoriteClickListener = { position ->
                val item = getItem(position)
                if (item!!.favorite) {
                    CoroutineScope(Dispatchers.IO).launch {
                        productRepository.cancelCollectProduct(item.productId)
                    }
                    Toast.makeText(
                        parent.context,
                        "Cancel Collect Success",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        productRepository.collectProduct(item.productId)
                    }
                    Toast.makeText(
                        parent.context,
                        "Collect Success",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    private fun getItem(position: Int): Product? {
        return if (position < 0 || position >= items.size) null else items[position]
    }

    class ViewHolder(
        private val binding: ViewDataBinding,
        onFavoriteClickListener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnLongClickListener {
                onFavoriteClickListener.invoke(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind(item: Product) {
            binding.apply {
                setVariable(BR.product, item)
                executePendingBindings()
            }
        }
    }
}