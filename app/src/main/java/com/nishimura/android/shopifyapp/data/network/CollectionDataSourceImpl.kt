package com.nishimura.android.shopifyapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import java.io.IOException

class CollectionDataSourceImpl(private val shopifyApiService: ShopifyApiService) :
    CollectionDataSource {
    private val _downloadedCurrentCollection = MutableLiveData<CustomCollectionsResponse>()
    override val downloadedCurrentCollection: LiveData<CustomCollectionsResponse>
        get() = _downloadedCurrentCollection
    override suspend fun fetchCurrentCollection() {
        try {
            val fetchCollection = shopifyApiService
                .getCollections()
                .await()
            _downloadedCurrentCollection.postValue(fetchCollection)
        }
        catch (e : IOException) {
            Log.e("CollectionDataSourceImp", "no internet connection", e)
        }
    }
}