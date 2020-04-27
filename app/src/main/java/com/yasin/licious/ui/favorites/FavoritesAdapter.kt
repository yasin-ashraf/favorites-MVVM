package com.yasin.licious.ui.favorites

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yasin.licious.data.model.UiProduct
import com.yasin.licious.databinding.FavoriteListItemBinding
import java.lang.Exception

/**
 * Created by Yasin on 27/4/20.
 */
class FavoritesAdapter(private val picasso: Picasso) : ListAdapter<UiProduct, ProductViewHolder>(
    ProductsItemCallback()
) {

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
        val product = currentList[position]
        holder.bind(product,picasso)
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

    private val ZERO: Double = 0.0

    fun bind(product: UiProduct, picasso: Picasso) {
        binding.tvActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.tvProductName.text = product.productName
        if(product.offerPrice == ZERO){
            binding.tvActualPrice.visibility = View.INVISIBLE
            binding.tvOfferPrice.text = String.format("\u20B9 %s",product.actualPrice)
        }else {
            binding.tvActualPrice.visibility = View.VISIBLE
            binding.tvOfferPrice.text = String.format("\u20B9 %s",product.offerPrice)
            binding.tvActualPrice.text = String.format("\u20B9 %s",product.actualPrice)
        }
        binding.tvQuantity.text = String.format("Net Wt. %s",product.netWt)
        picasso
            .load(product.image)
            .fit()
            .centerCrop()
            .into(binding.ivItem, object : Callback {
                override fun onSuccess() {
                    Log.d("Picasso load Image","Success")
                }

                override fun onError(e: Exception?) {
                    Log.e("Picasso load Image",e.toString())
                }

            })
    }
}