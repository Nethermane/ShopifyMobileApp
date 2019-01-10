package com.nishimura.android.shopifyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import com.nishimura.android.shopifyapp.data.db.entity.CollectionDao
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit
import com.nishimura.android.shopifyapp.data.network.CollectionDataSource
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse

class CollectionRepositoryImpl(
    private val collectionDao: CollectionDao,
    private val collectionDataSource: CollectionDataSource
) : CollectionRepository {

    init {
        //Observe forever because when this repository is destroyed the app is as well
        collectionDataSource.downloadedCurrentCollection.observeForever { newCollection ->
            //Send to local database
            persistCollection(newCollection)
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
    private suspend fun initCollectionData() {
        //Temp set always true
        if(isFetchedCurrentNeeded(ZonedDateTime.now().minusMinutes(31)))
            fetchCurrentCollection()

    }
    private suspend fun fetchCurrentCollection() {
        //Gets data then updates it, observed in init
        collectionDataSource.fetchCurrentCollection()
    }
    private fun isFetchedCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}