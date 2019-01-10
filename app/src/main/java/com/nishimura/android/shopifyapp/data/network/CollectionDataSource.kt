package com.nishimura.android.shopifyapp.data.network

import androidx.lifecycle.LiveData
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse

interface CollectionDataSource {
    val downloadedCurrentCollection: LiveData<CustomCollectionsResponse>
    suspend fun fetchCurrentCollection()
}