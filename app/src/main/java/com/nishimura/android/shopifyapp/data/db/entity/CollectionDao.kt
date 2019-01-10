package com.nishimura.android.shopifyapp.data.db.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nishimura.android.shopifyapp.data.db.unit.CollectionUnit

@Dao
interface CollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(collectionEntry: CustomCollectionEntry)
    @Query("select * from custom_collection")
    fun getCollectionTitle(): LiveData<List<CollectionUnit>>
}