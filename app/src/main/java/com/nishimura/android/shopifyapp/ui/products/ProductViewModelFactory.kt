package com.nishimura.android.shopifyapp.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nishimura.android.shopifyapp.data.repository.CollectionRepository

class ProductViewModelFactory(
    private val repository: CollectionRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(repository) as T
    }
}
