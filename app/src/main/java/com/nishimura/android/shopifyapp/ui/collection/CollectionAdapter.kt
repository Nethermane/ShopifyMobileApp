package com.nishimura.android.shopifyapp.ui.collection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.DialogTitle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.collection_list_item.view.*
import com.nishimura.android.shopifyapp.R
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.internal.BindableAdapter
import androidx.annotation.NonNull
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import android.widget.TextView
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData


internal class CollectionAdapter(
    private val context: Context
) : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private var data: List<CollectionUnit> = emptyList()
    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val itemView = layoutInflater.inflate(R.layout.collection_list_item, parent, false)
        return CollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<CollectionUnit>) {
        if (data.isNotEmpty()) {
            val postDiffCallback = CollectionDiffCallback(data, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)

            data = newData
            diffResult.dispatchUpdatesTo(this)
        } else {
            // first initialization
            data = newData
            Toast.makeText(context,data.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    internal inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val collection_title: TextView = itemView.findViewById(R.id.collection_title)
        // private val tvContent: TextView
        //private val btnDelete: Button

        init {

            // tvContent = itemView.findViewById(R.id.tvContent)
            //btnDelete = itemView.findViewById(R.id.btnDelete)
        }

        fun bind(collectionUnit: CollectionUnit?) {
            if (collectionUnit != null) {
                collection_title.text = collectionUnit.title
//                tvContent.setText(post!!.getContent())
//                btnDelete.setOnClickListener({ v -> onDeleteButtonClickListener?.onDeleteButtonClicked(post) })

            }
        }

    }

    internal inner class CollectionDiffCallback(private val oldPosts: List<CollectionUnit>, private val newPosts: List<CollectionUnit>) :
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