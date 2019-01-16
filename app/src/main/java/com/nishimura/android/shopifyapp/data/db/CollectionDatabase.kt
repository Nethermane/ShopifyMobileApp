package com.nishimura.android.shopifyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nishimura.android.shopifyapp.data.db.entity.dao.CollectionDao
import com.nishimura.android.shopifyapp.data.db.entity.dao.ProductDao
import com.nishimura.android.shopifyapp.data.db.entity.dao.ProductIdDao
import com.nishimura.android.shopifyapp.data.db.entity.entry.CollectEntry
import com.nishimura.android.shopifyapp.data.db.entity.entry.CustomCollectionEntry
import com.nishimura.android.shopifyapp.data.db.entity.entry.ProductEntry
import com.nishimura.android.shopifyapp.utilities.DataConverter

@Database(
    entities = [CustomCollectionEntry::class, CollectEntry::class, ProductEntry::class],
    version = 8
)
@TypeConverters(DataConverter::class)
abstract class CollectionDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
    abstract fun productDao(): ProductDao
    abstract fun productIdDao(): ProductIdDao
    companion object {
        @Volatile private var instance: CollectionDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context:Context) = instance?: synchronized(LOCK) {
            instance?: buildDatabase(context).also{instance = it}
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CollectionDatabase::class.java, "collection.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}