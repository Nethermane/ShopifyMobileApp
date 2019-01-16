package com.nishimura.android.shopifyapp.ui.products

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nishimura.android.shopifyapp.GlideApp
import com.nishimura.android.shopifyapp.R
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductUnit


internal class ProductAdapter(
    private val context: Context, private val collectionUnit: CollectionUnit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var data: List<ProductUnit> = emptyList()
    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = layoutInflater.inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<ProductUnit>) {
        if (data.isNotEmpty()) {
            val postDiffCallback = ProductDiffCallback(data, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)

            data = newData
            diffResult.dispatchUpdatesTo(this)
        } else {
            // first initialization
            data = newData
            notifyDataSetChanged()
        }
    }

    internal inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val productTitle: TextView = itemView.findViewById(R.id.product_title)
        private val collectionImage: ImageView = itemView.findViewById(R.id.collection_image)
        private val productStock: TextView = itemView.findViewById(R.id.product_stock)
        private val collectionTitle: TextView = itemView.findViewById(R.id.product_collection)

        fun bind(productUnit: ProductUnit?) {
            if (productUnit != null) {
                productTitle.text = productUnit.title
                GlideApp.with(context).load(productUnit.image_src)
                    .into(collectionImage)
                productStock.text = context.getString(R.string.total_stock, productUnit.variants.map{it.inventoryQuantity}.sum())
                collectionTitle.text = collectionUnit.title
            }
        }

    }

    internal inner class ProductDiffCallback(
        private val oldPosts: List<ProductUnit>,
        private val newPosts: List<ProductUnit>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldPosts.size
        }

        override fun getNewListSize(): Int {
            return newPosts.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPosts[oldItemPosition] === newPosts[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPosts[oldItemPosition].title == newPosts[newItemPosition].title
        }
    }
}