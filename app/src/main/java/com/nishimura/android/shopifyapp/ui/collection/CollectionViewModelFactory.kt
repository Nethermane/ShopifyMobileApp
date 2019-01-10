package com.nishimura.android.shopifyapp.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nishimura.android.shopifyapp.data.repository.CollectionRepository

class CollectionViewModelFactory(
    private val repository: CollectionRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CollectionViewModel(repository) as T
    }
}
