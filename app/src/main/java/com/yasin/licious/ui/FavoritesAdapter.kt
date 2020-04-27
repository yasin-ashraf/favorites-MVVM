package com.yasin.licious.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yasin.licious.data.model.UiProduct
import com.yasin.licious.databinding.FavoriteListItemBinding
import kotlinx.android.synthetic.main.favorite_list_item.view.*

/**
 * Created by Yasin on 27/4/20.
 */
class FavoritesAdapter : ListAdapter<UiProduct, ProductViewHolder>(ProductsItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            FavoriteListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemView.tv_actual_price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//        val product = currentList[position]
//        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return 40
    }

}

class ProductsItemCallback : DiffUtil.ItemCallback<UiProduct>() {

    override fun areContentsTheSame(oldItem: UiProduct, newItem: UiProduct): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areItemsTheSame(oldItem: UiProduct, newItem: UiProduct): Boolean {
        return oldItem == newItem
    }
}

class ProductViewHolder(private val binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: UiProduct) {
        binding.tvActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}