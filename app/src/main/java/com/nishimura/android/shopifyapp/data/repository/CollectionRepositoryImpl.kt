package com.nishimura.android.shopifyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import com.nishimura.android.shopifyapp.data.db.entity.CollectionDao
import com.nishimura.android.shopifyapp.data.db.entity.ProductDao
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.db.unit.ProductIdUnit
import com.nishimura.android.shopifyapp.data.network.CollectionDataSource
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse

class CollectionRepositoryImpl(
    private val collectionDao: CollectionDao,
    private val collectionDataSource: CollectionDataSource,
    private val productDao: ProductDao
) : CollectionRepository {

    init {
        //Observe forever because when this repository is destroyed the app is as well
        collectionDataSource.downloadedCurrentCollection.observeForever { newCollection ->
            //Send to local database
            persistCollection(newCollection)
        }
        collectionDataSource.downloadedProductIDsResponse.observeForever { newProductId ->
            persistProductIds(newProductId)
        }
    }
    override suspend fun getProductIDsFromCollectionID(collectionId: String): LiveData<List<ProductIdUnit>> {
        return withContext(Dispatchers.IO) {
            initProductIdData()
            return@withContext productDao.getProductIdsFromCollection(collectionId)
        }
    }
    override suspend fun getCollections(): LiveData<List<CollectionUnit>> {
        return withContext(Dispatchers.IO) {
            initCollectionData()
            return@withContext collectionDao.getCollectionTitle()
        }
    }

    private fun persistCollection(fetchedCollection: CustomCollectionsResponse) {
        //Global scope is viable here because this app will always be alive if this class is alive
        GlobalScope.launch(Dispatchers.IO) {
            for (collection in fetchedCollection.customCollectionEntries) {
                collectionDao.upsert(collection)
            }

        }
    }
    private fun persistProductIds(fetchedCollection: ProductIDsResponse) {
        //Global scope is viable here because this app will always be alive if this class is alive
        GlobalScope.launch(Dispatchers.IO) {
            for (collection in fetchedCollection.collects) {
                productDao.upsert(collection)
            }

        }
    }
    private suspend fun initCollectionData() {
        //Temp set always true
        if(isFetchedCurrentNeeded(ZonedDateTime.now().minusMinutes(31)))
            fetchCurrentCollection()
    }
    private suspend fun initProductIdData() {
        //Temp set always true
        if(isFetchedCurrentNeeded(ZonedDateTime.now().minusMinutes(31)))
            fetchCurrentProductIds()

    }
    private suspend fun fetchCurrentCollection() {
        //Gets data then updates it, observed in init
        collectionDataSource.fetchCurrentCollection()
    }
    private suspend fun fetchCurrentProductIds() {
        //Gets data then updates it, observed in init
        collectionDataSource.fetchProductsFromCollectionId()
    }
    private fun isFetchedCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}