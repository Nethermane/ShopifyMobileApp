package com.nishimura.android.shopifyapp.data.repository

import androidx.lifecycle.LiveData
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit

interface CollectionRepository {
    suspend  fun getCollections() : LiveData<List<CollectionUnit>>
}